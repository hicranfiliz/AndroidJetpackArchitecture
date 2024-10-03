package com.example.twowaydemo1

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.twowaydemo1.databinding.ActivityTwoWayExpBinding

class TwoWayExpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTwoWayExpBinding
    private lateinit var viewModel: TwoWayExpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_exp)
        viewModel = ViewModelProvider(this).get(TwoWayExpViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}