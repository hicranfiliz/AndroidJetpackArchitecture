package com.example.coroutiensdemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

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

        // async and await paralel islem yurutmeyi saglar..
        CoroutineScope(Main).launch {
            Log.i("MyTag: " , "Calculate started...")
            val stock1 = async(IO) {
                getStock1()
            }
            val stock2 = async(IO) {
                getStock2()
            }
            val total = stock1.await() + stock2.await()
            Toast.makeText(applicationContext, "Total is $total", Toast.LENGTH_SHORT).show()
            //Log.i("MyTag: " , "Total is $total")
        }

        findViewById<Button>(R.id.btnCount).setOnClickListener {
            findViewById<TextView>(R.id.tvCount).text = count++.toString()
        }

        findViewById<Button>(R.id.btnDownloadUserData).setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                findViewById<TextView>(R.id.tvUserMessage).text = UserDataManager1().getTotalUserCount().toString()
                //downloadUserData()
                //Log.i("Launch", "Running in ${Thread.currentThread().name}")
            }

//            CoroutineScope(Dispatchers.Main).launch {
//                //downloadUserData()
//                Log.i("Launch", "Running in ${Thread.currentThread().name}")
//            }


        }
    }

    // switch the thread of a coroutine.
    // coroutine arka planda baslatilir ve ui guncellenmesi gerektigi icin main thread2 tasinir..

    //suspended fonksiyonlari thread blocklairndan kacinmak icn ve daha iyi bir kullanici deneyimi icin kullaniriz.
    private suspend fun downloadUserData() {
        for (i in 1..200000){
            withContext(Dispatchers.Main){
                findViewById<TextView>(R.id.tvUserMessage).text = "Dowloading user $i in ${Thread.currentThread().name}"
            }
            //Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
        }
    }

    private suspend fun getStock1() : Int{
        delay(10000)
        Log.i("MyTag", " stock 1 returned.")
        return 55000
    }

    private suspend fun getStock2() : Int{
        delay(8000)
        Log.i("MyTag", " stock 2 returned.")
        return 35000
    }
}