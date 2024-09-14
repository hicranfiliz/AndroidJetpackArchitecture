package com.example.viewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IncreaseNumViewModel : ViewModel() {

    private var number = MutableLiveData<Int>()
    val numberValue : LiveData<Int>
        get() = number

    // initial value icin init blogu tanimliyorum

    init {
        number.value = 0
    }

    fun increaseNum(){
        number.value = number.value!! + 1
    }
}