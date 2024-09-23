package com.example.viewmodeldemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo.databinding.ActivityIncreaseNumBinding

class IncreaseNumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIncreaseNumBinding
    private lateinit var viewModel: IncreaseNumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_increase_num)
        viewModel = ViewModelProvider(this).get(IncreaseNumViewModel::class.java)
        // bunu tanimlarsak altta viewmodelı observe etmeye gerek yok.
        binding.lifecycleOwner = this
        binding.myviewModel = viewModel

//        viewModel.numberValue.observe(this) {
//            binding.tvIncreasedNm.text = it.toString()
//        }

        // databinding ile xmle onclick fonk tanimladigimiz icin buraya gerek yok
//        binding.btnClick.setOnClickListener {
//           // binding.tvIncreasedNm.text = viewModel.increaseNum().toString()
//            viewModel.increaseNum()
//        }
    }
}