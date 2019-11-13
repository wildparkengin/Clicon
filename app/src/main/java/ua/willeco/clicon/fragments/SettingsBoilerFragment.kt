package ua.willeco.clicon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.willeco.clicon.R
import ua.willeco.clicon.fragments.fragment_factory.AppFragment

class SettingsBoilerFragment : AppFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_boiler_settings, container, false)



        return view
    }
}