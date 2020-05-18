package ua.willeco.clicon.di.component

import dagger.Component
import ua.willeco.clicon.di.module.ApiServiceModule
import ua.willeco.clicon.di.module.ApplicationModule
import ua.willeco.clicon.ui.AuthorizationActivity
import ua.willeco.clicon.ui.MainActivity

@Component(modules = [(ApiServiceModule::class),(ApplicationModule::class)])
interface AuthApplicationComponent {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param authActivity PostPresenter in which to inject the dependencies
     */
    fun inject(authActivity: AuthorizationActivity)
    fun inject (mainActivity: MainActivity)
}