package com.example.architecturerecyclerviewdemo

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val fruitsList = listOf<Fruit>(
        Fruit("Mango", "Hicran"),
        Fruit("Orange", "Hicran"),
        Fruit("Strawberry", "Hicran"),
        Fruit("Apple", "Hicran"),
        Fruit("Peach", "Hicran"),
        Fruit("Kiwi", "Hicran"),
        Fruit("Berry", "Hicran"),
        Fruit("Carrot", "Hicran"),
        Fruit("Pineapple", "Hicran"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.setBackgroundColor(Color.YELLOW)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(fruitsList) { selectedItem: Fruit ->
            listItemClicked(selectedItem)
        }

    }

    private fun listItemClicked(fruit: Fruit){
        Toast.makeText(this, "Suplier name is ${fruit.supplier}", Toast.LENGTH_SHORT).show()
    }
}