package ua.willeco.clicon.fragments

import android.os.Bundle

class EditorFragment : androidx.fragment.app.Fragment() {

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