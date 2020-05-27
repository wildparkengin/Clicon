package ua.willeco.clicon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.R
import ua.willeco.clicon.model.Facility

class FacilitiesAdapter(private val context: Context, private var faciliList: ArrayList<Facility>) : RecyclerView.Adapter<FacilitiesAdapter.FacilitiesViewHolder>()  {

    class FacilitiesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_facility, parent, false)) {
        private var mTitleFacility: TextView? = null
        private var mYearView: TextView? = null


        init {
            mTitleFacility = itemView.findViewById(R.id.txt_facility_name)
            mYearView = itemView.findViewById(R.id.txt_facility_count_devices)
        }

        fun bind(facility: Facility) {
            mTitleFacility?.text = facility.name
            mYearView?.text = facility.devices_count.toString()
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
    }

}