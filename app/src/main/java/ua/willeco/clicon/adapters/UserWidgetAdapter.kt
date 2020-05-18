package ua.willeco.clicon.adapters

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ua.willeco.clicon.R
import ua.willeco.clicon.model.TestModel

class UserWidgetAdapter(private val userWidgetList: ArrayList<TestModel>):

    RecyclerView.Adapter<UserWidgetAdapter.ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_widget_item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userWidgetList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = userWidgetList[position].name
        //holder.count?.text = userWidgetList[position].count.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txt_add_widget_title)
        //val count = itemView.findViewById<TextView>(R.id.tvCount)
    }
}

