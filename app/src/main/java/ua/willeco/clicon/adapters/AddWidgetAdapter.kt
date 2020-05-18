package ua.willeco.clicon.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.R
import ua.willeco.clicon.model.widgets.EnableWidget
import ua.willeco.clicon.enums.AvailaibleWidget

class AddWidgetAdapter(private val context:Context, private val widgetList: ArrayList<EnableWidget>):

    RecyclerView.Adapter<AddWidgetAdapter.ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_widget_item_layout,parent,false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return widgetList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = widgetList[position].wTitle
        holder.imgBg.background = getUserDrawable(widgetList[position].wBackground)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txt_add_widget_title)
        val imgPlus: ImageView = itemView.findViewById(R.id.img_widget_add_plus)
        val imgBg: ImageView = itemView.findViewById(R.id.img_widget_add_bg)
        val crAddWidget: CardView = itemView.findViewById(R.id.crd_add_widget)
    }

    private fun getUserDrawable(type:String): Drawable? {
        return when(type){
            AvailaibleWidget.BOILER.typeItem-> ContextCompat.getDrawable(context, R.drawable.wpeco)
            AvailaibleWidget.CLICON.typeItem -> ContextCompat.getDrawable(context, R.drawable.clicon)
            AvailaibleWidget.STATISTIC.typeItem -> ContextCompat.getDrawable(context, R.drawable.widget_statistic)
            else ->{
                ContextCompat.getDrawable(context, R.drawable.clicon)
            }
        }
    }
}
