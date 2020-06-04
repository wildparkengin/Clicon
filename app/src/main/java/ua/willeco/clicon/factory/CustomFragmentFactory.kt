package ua.willeco.clicon.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ua.willeco.clicon.ui.FacilitiesFragment

class CustomFragmentFactory:FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            FacilitiesFragment::class.java.name -> FacilitiesFragment()
            else -> {
                return super.instantiate(classLoader, className)
            }
        }
    }
}