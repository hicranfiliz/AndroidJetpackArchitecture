package com.example.roomdemo

import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberRepository
import com.google.android.material.color.utilities.DislikeAnalyzer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.nio.file.Files.delete

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateAndDelete : Subscriber

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){
        if (inputName.value == null){
            statusMessage.value = Event("Please enter a name!")
        }else if(inputEmail.value== null){
            statusMessage.value = Event("Please enter an email!")
        }else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()){
            statusMessage.value = Event("Please enter a valid email address!")
        }else{
            if (isUpdateOrDelete){
                subscriberToUpdateAndDelete.name = inputName.value!!
                subscriberToUpdateAndDelete.email = inputEmail.value!!
                update(subscriberToUpdateAndDelete)
            }else{
                val name = inputName.value!!
                val email = inputEmail.value!!
                insert(Subscriber(0, name, email))
                inputName.value = ""
                inputEmail.value = ""
            }
        }
    }

    fun clearAllOrDelete(){
        if (isUpdateOrDelete){
            delete(subscriberToUpdateAndDelete)
        }else{
            clearAll()
        }
    }

    fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO){
        val newRowId = repository.insert(subscriber)
        withContext(Dispatchers.Main) {
            if (newRowId > -1){
                statusMessage.value = Event("Subscriber inserted successfully! $newRowId")
            }else{
                statusMessage.value = Event("Error occured!")
            }

        }
    }

    fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRows = repository.update(subscriber)
        withContext(Dispatchers.Main){
            if (numberOfRows > 0){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("$numberOfRows row of Subscriber updated successfully!")
            }else{
                statusMessage.value = Event("Error Occured!")
            }

        }
    }

    fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRowsDeleted = repository.delete(subscriber)
        withContext(Dispatchers.Main){
            if (numberOfRowsDeleted > 0){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("$numberOfRowsDeleted Subscriber deleted successfully!")
            }else{
                statusMessage.value = Event("Error Occured!")
            }

        }
    }

    private fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRowsDeleted = repository.deleteAll()
        withContext(Dispatchers.Main){
            if (numberOfRowsDeleted > 0){
                statusMessage.value = Event("$numberOfRowsDeleted deleted successfully!")
            }else{
                statusMessage.value = Event("Error Occured!")
            }

        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateAndDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }
}