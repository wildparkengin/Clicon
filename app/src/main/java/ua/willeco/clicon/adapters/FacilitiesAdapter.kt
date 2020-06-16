package ua.willeco.clicon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.R
import ua.willeco.clicon.enums.AppRequestEventType
import ua.willeco.clicon.model.Facility
import ua.willeco.clicon.mvp.view.PopupEditListener

class FacilitiesAdapter(private val context: Context,private val listener: PopupEditListener, private var faciliList: ArrayList<Facility>) : RecyclerView.Adapter<FacilitiesAdapter.FacilitiesViewHolder>()  {

    private var mPopupMenu:PopupMenu? = null

    class FacilitiesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_facility, parent, false)){
        private var mTitleFacility: TextView? = null
        private var mDevicesCount: TextView? = null
        private var mItemLayout:CardView? = null

        init {
            mTitleFacility = itemView.findViewById(R.id.txt_facility_name)
            mDevicesCount = itemView.findViewById(R.id.txt_facility_count_devices)
            mItemLayout = itemView.findViewById(R.id.card_device_item)
        }

        fun bind(facility: Facility) {
            mTitleFacility?.text = facility.name
            mDevicesCount?.text = facility.devices_count.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilitiesViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return FacilitiesViewHolder(layoutInflater,parent)
    }

    override fun getItemCount(): Int {
        return faciliList.size
    }

    override fun onBindViewHolder(holder: FacilitiesViewHolder, position: Int) {
        holder.bind(faciliList[position])
        holder.itemView.setOnLongClickListener{
            showPopupMenu(context,it, position)
            true
        }
    }

    private fun showPopupMenu(context: Context,mView: View, pos:Int){
        mPopupMenu = PopupMenu(context,mView)
        mPopupMenu?.inflate(R.menu.popup_menu)

        val paramAddString = faciliList[pos].name

        val mChange = mPopupMenu?.menu?.findItem(R.id.mnuPopupChange).also {
            if (it != null) {
                it.title = it.title.toString().plus(" $paramAddString")
            }
        }
        val mDelete = mPopupMenu?.menu?.findItem(R.id.mnuPopupDelete).also {
            if (it != null) {
                it.title = it.title.toString().plus(" $paramAddString")
            }
        }

        mPopupMenu?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.mnuPopupChange -> {
                    listener.changeItem(AppRequestEventType.UPDATE_FACILITY, faciliList[pos])
                }
                R.id.mnuPopupDelete -> {
                    listener.deleteItem(AppRequestEventType.DELETE_FACILITY,faciliList[pos].mac.toString())
                }
            }
            false
        }

        mPopupMenu?.show()
    }
}