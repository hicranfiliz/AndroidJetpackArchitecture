package com.example.architecturerecyclerviewdemo

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(
    private val fruitList: List<Fruit>,
    private val clickListener: (Fruit) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // item_list layoutunu inflate et.
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.bind(fruit, clickListener)
    }

}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){

    fun bind(fruit: Fruit, clickListener: (Fruit) -> Unit){
        val myTextView = view.findViewById<TextView>(R.id.tvName)
        val myTextView2 = view.findViewById<TextView>(R.id.tvSupplier)

        view.setOnClickListener {
            clickListener(fruit)
        }
    }
}