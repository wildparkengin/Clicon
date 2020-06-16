package ua.willeco.clicon.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ua.willeco.clicon.ui.HomeFragment

class SelectedFragmentFactory:FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            HomeFragment::class.java.name -> HomeFragment()
            else -> {
                return super.instantiate(classLoader, className)
            }
        }
    }
}