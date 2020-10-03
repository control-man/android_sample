package com.jinss.android.androidsample.recyclerview.itemanimator

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jinss.android.androidsample.R

class ItemAdapter : RecyclerView.Adapter<MyItemViewHolder>() {

    var data: List<MyItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemViewHolder {
        return MyItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_itemanimator_myitem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        println("onBindViewHolder current position: $position ")
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}

class MyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.my_item)

    fun bind(item: MyItem) {
        textView.text = item.text
    }
}
