package com.wildpark.clicon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.wildpark.clicon.R

class LeftGroupWidget:Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.backdrop_fragment, container,false)



        return view
    }


    fun initWidgets(view: View){
        val card_new_camera_widget = view.findViewById<CardView>(R.id.crd_camera_widget)
        val card_new_boiler_widget = view.findViewById<CardView>(R.id.crd_camera_widget)
        val card_new_clicon_widget = view.findViewById<CardView>(R.id.crd_camera_widget)
        val card_new_statistic_widget = view.findViewById<CardView>(R.id.crd_camera_widget)
        val card_new_weather_widget = view.findViewById<CardView>(R.id.crd_camera_widget)
        val card_new_messages_widget = view.findViewById<CardView>(R.id.crd_camera_widget)


        card_new_camera_widget.setOnClickListener {
            println("asdasd")
        }

        card_new_boiler_widget.setOnClickListener {
            println("asdasd")
        }

        card_new_clicon_widget.setOnClickListener {
            println("asdasd")
        }

        card_new_statistic_widget.setOnClickListener {
            println("asdasd")
        }

        card_new_weather_widget.setOnClickListener {
            println("asdasd")
        }


    }
}