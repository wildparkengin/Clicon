package ua.willeco.clicon.fragments

import android.os.Bundle
import ua.willeco.clicon.fragments.fragment_factory.AppFragment

class EditorFragment : AppFragment() {

    companion object{
        var KEY_POSITION:String = "position"
        private lateinit var arguments:Bundle

        fun newInstance(position: Int) = EditorFragment.apply {
            arguments = Bundle().apply {
                putInt("REPLACE WITH A STRING CONSTANT", position)
            }
        }

        fun getTitle(position: Int):String{
            return String.format("sfdsdf")
        }

    }

}