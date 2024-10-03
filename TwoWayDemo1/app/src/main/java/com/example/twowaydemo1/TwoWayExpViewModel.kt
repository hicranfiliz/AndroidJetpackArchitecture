package com.example.twowaydemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TwoWayExpViewModel(startingValue: Int): ViewModel() {

    private var total = MutableLiveData<Int>()
    val totalData : LiveData<Int>
        get() = total

    init {
        total.value = startingValue
    }

    fun setTotal(input : Int){
        total.value = (total.value)?.plus(input)
    }

}