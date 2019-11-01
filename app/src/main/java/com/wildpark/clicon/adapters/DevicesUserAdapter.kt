package com.wildpark.clicon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wildpark.clicon.R
import com.wildpark.clicon.entities.TestModel

class DevicesUserAdapter (private val userWidgetList: ArrayList<TestModel>):RecyclerView.Adapter<DevicesUserAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_devices_item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userWidgetList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = userWidgetList[position].name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txt_device_title)
        //val count = itemView.findViewById<TextView>(R.id.tvCount)
    }
}