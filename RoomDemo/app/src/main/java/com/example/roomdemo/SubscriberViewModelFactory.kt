package com.example.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.db.SubscriberRepository

class SubscriberViewModelFactory(private val repository : SubscriberRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}