package com.example.viewmodeldemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodeldemo.databinding.ActivityCalculateSumBinding

class CalculateSumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculateSumBinding
    private lateinit var viewModel: CalculateActivityViewModel
    private lateinit var viewModelFactory: CalculateActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_calculate_sum)
        viewModelFactory = CalculateActivityViewModelFactory(85)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CalculateActivityViewModel::class.java)

        binding.tvSum.text = viewModel.getSum().toString()
        binding.btnAdd.setOnClickListener {
            var number = binding.etNum.text.toString().toInt()
            if (number != 0) {
                viewModel.updateSum(number)
                binding.tvSum.text = viewModel.getSum().toString()
            } else{
                binding.tvSum.text = "Please enter greater than 0"
            }

        }
    }
}