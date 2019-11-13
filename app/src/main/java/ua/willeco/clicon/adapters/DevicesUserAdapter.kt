package ua.willeco.clicon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.R
import ua.willeco.clicon.entities.TestModel

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
        holder.btnDeleteDevice.setOnClickListener{
            if (userDevicesList.isNotEmpty()){
                if (userDevicesList.size<2){
                    Toast.makeText(context,"Fuck off",Toast.LENGTH_SHORT).show()
                }else{
                    userDevicesList.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txt_device_title)
        val btnDeleteDevice: Button = itemView.findViewById(R.id.btn_delete_device)
        //val count = itemView.findViewById<TextView>(R.id.tvCount)
    }
}