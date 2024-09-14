package com.example.viewmodeldemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CalculateActivityViewModelFactory(private val startingValue : Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculateActivityViewModel::class.java)){
            return CalculateActivityViewModel(startingValue) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}