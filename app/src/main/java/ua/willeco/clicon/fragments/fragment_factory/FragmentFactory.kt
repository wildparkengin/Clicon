package ua.willeco.clicon.fragments.fragment_factory

import ua.willeco.clicon.enums.AppSection
import ua.willeco.clicon.fragments.FragmentDevicesList

class FragmentFactory {
    fun getFragment(section:AppSection): AppFragment? {
        return when (section) {
            AppSection.DEVICE_LIST_FRAGMENT -> FragmentDevicesList()
        }
    }
}