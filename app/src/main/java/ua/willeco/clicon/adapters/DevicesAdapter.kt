package ua.willeco.clicon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.R

class DevicesAdapter (private val devicesList: ArrayList<String>):RecyclerView.Adapter<DevicesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_devices_item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return devicesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = devicesList[position].toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txt_device_title)
        val cardItem:CardView = itemView.findViewById(R.id.card_device_item)
    }
}