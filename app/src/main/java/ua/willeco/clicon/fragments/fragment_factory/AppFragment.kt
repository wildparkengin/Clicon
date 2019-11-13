package ua.willeco.clicon.fragments.fragment_factory

import androidx.fragment.app.Fragment
import ua.willeco.clicon.MainActivity

abstract class AppFragment :Fragment(){
    fun getFragmentTag(): String {
        return this.javaClass.simpleName
    }
    fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
}