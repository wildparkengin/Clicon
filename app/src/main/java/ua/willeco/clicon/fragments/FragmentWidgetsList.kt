package ua.willeco.clicon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.willeco.clicon.R
import ua.willeco.clicon.adapters.UserWidgetAdapter
import ua.willeco.clicon.entities.TestModel
import ua.willeco.clicon.fragments.fragment_factory.AppFragment

class FragmentWidgetsList:AppFragment() {

    lateinit var _view:View
    lateinit var recyclerWidgets: RecyclerView

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _view = inflater.inflate(R.layout.fragment_widgets_list, container, false)
        recyclerWidgets = _view.findViewById(R.id.rcv_widgets_list)

        initRecyclerWidgets()

        return _view
    }


    private fun initRecyclerWidgets(){
        val dataWidget = ArrayList<TestModel>()
        var typeAdapter: RecyclerView.Adapter<*>? = null
        dataWidget.add(TestModel("Phone", 1))
        dataWidget.add(TestModel("Watch", 2))
        dataWidget.add(TestModel("Note", 3))
        dataWidget.add(TestModel("Pin", 4))

        typeAdapter = UserWidgetAdapter(dataWidget)

        recyclerWidgets.layoutManager = LinearLayoutManager(_view.context, RecyclerView.VERTICAL, false)

        recyclerWidgets.adapter = typeAdapter
        (recyclerWidgets.adapter as RecyclerView.Adapter<*>).notifyDataSetChanged()
    }
}