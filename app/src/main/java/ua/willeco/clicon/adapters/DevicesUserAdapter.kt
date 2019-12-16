package ua.willeco.clicon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.R
import ua.willeco.clicon.entities.TestModel
import ua.willeco.clicon.enums.EventType
import ua.willeco.clicon.event_bus.RxBus
import ua.willeco.clicon.event_bus.RxEvent

class DevicesUserAdapter (private val userDevicesList: ArrayList<TestModel>):RecyclerView.Adapter<DevicesUserAdapter.ViewHolder>(){

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_devices_item_layout,parent,false)
        context = parent.context
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userDevicesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = userDevicesList[position].name
        holder.cardItem.setOnClickListener {
            RxBus.publish(RxEvent.EventChanges(EventType.OPEN_BOILER_SETTINGS,userDevicesList[position].name))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txt_device_title)
        val cardItem:CardView = itemView.findViewById(R.id.card_device_item)
    }
}