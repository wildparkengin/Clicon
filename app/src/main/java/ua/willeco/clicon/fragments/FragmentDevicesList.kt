package ua.willeco.clicon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.R
import ua.willeco.clicon.adapters.DevicesUserAdapter
import ua.willeco.clicon.entities.TestModel
import ua.willeco.clicon.fragments.fragment_factory.AppFragment

class FragmentDevicesList :AppFragment() {
    lateinit var _view:View
    lateinit var recyclerDevices:RecyclerView

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _view = inflater.inflate(R.layout.fragment_device_list, container, false)
        recyclerDevices = _view.findViewById(R.id.rcv_main)

        initDeviceRecycler()

        return _view
    }

    fun initDeviceRecycler() {

        val txtCardAddItemType: TextView = _view.findViewById(R.id.txt_type_recycler_item_add)

        recyclerDevices.layoutManager = LinearLayoutManager(_view.context, RecyclerView.VERTICAL, false)

        val dataWidget = ArrayList<TestModel>()
        var typeAdapter: RecyclerView.Adapter<*>? = null

        dataWidget.add(TestModel("Phone", 1))
        dataWidget.add(TestModel("Watch", 2))
        dataWidget.add(TestModel("Note", 3))
        dataWidget.add(TestModel("Pin", 4))

        typeAdapter = DevicesUserAdapter(dataWidget)
        txtCardAddItemType.text = "Add device"

        recyclerDevices.adapter = typeAdapter
        (recyclerDevices.adapter as RecyclerView.Adapter<*>).notifyDataSetChanged()
    }
}