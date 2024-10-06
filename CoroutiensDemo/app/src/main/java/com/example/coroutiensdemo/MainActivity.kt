package com.example.coroutiensdemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnCount).setOnClickListener {
            findViewById<TextView>(R.id.tvCount).text = count++.toString()
        }

        findViewById<Button>(R.id.btnDownloadUserData).setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                //downloadUserData()
                Log.i("Launch", "Running in ${Thread.currentThread().name}")
            }

            CoroutineScope(Dispatchers.Main).launch {
                //downloadUserData()
                Log.i("Launch", "Running in ${Thread.currentThread().name}")
            }


        }
    }

    private fun downloadUserData() {
        for (i in 1..200000){
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
        }
    }
}