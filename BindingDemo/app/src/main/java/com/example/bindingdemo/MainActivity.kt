package com.example.bindingdemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.bindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnStart.setOnClickListener {
            stopOrStartProgressBar()
        }

        binding.btnSubmit.setOnClickListener {
            displayGreetings()
        }
    }

    private fun displayGreetings(){
        // we can use scoope functions to reduce using binding text two times.
        binding.apply {
            tvname.text = "Hello ${etname.text}"
        }

        //binding.tvname.text = "Hello " + binding.etname.text
    }

    private fun stopOrStartProgressBar(){

        binding.apply {
            if (progressBar.visibility == View.GONE){
                progressBar.visibility = View.VISIBLE
                btnStart.text = "STOP"
            }else{
                progressBar.visibility = View.GONE
                btnStart.text = "START"
            }
        }
    }
}