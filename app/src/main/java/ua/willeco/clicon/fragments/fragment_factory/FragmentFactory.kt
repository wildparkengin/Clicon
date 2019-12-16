package ua.willeco.clicon.fragments.fragment_factory

import ua.willeco.clicon.enums.AppSection
import ua.willeco.clicon.fragments.FragmentDevicesList
import ua.willeco.clicon.fragments.FragmentWidgetsList
import ua.willeco.clicon.fragments.SettingsBoilerFragment

class FragmentFactory {
    fun getFragment(section:AppSection): AppFragment? {
        return when (section) {
            AppSection.WIDGETS_LIST_FRAGMENT ->{FragmentWidgetsList()}
            AppSection.DEVICE_LIST_FRAGMENT -> {FragmentDevicesList()}
            AppSection.BOILER_SETTINGS_FRAGMENT -> {SettingsBoilerFragment()}
            AppSection.CLICON_SETTINGS_FRAGMENT -> {return null}
        }
    }
}