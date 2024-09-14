package com.example.viewmodeldemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculateActivityViewModel(startingValue : Int) : ViewModel() {

    // sum edgiskenini observe edeceğim icin prvate kaldirdim.
    //private var sum = 0

    // Burada sum degiskeni surekli update edilecegi icin mutableLiveData kullanmaliyim.
    // Ama burada MutableLiveData acik hle geldi. Nasıl encapsule edebilrim?
    // Bunu private yapip, bir basla public livedata daha tanimlayip, bir getter fonksiyonundan
    // LiveData objesi dondurecek backing property kullanabilirm..
    private var sum = MutableLiveData<Int>()
    val sumData : LiveData<Int>
        get() = sum

    init {
        sum.value = startingValue
    }

    fun updateSum(number : Int){
        sum.value = (sum.value)?.plus(number)
    }
}