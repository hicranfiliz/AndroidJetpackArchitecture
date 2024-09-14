package com.example.viewmodeldemo

import androidx.lifecycle.ViewModel

class CalculateActivityViewModel(startingValue : Int) : ViewModel() {

    private var sum = 0

    init {
        sum = startingValue
    }

    fun getSum() : Int {
        return sum
    }

    fun updateSum(number : Int) : Int{
        sum += number
        return sum
    }
}