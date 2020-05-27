package ua.willeco.clicon.di.component

import dagger.BindsInstance
import dagger.Component
import ua.willeco.clicon.di.module.ApiServiceModule
import ua.willeco.clicon.di.module.ContextModule
import ua.willeco.clicon.di.module.SharedPreferencesModule
import ua.willeco.clicon.mvp.presenter.AuthorizationActivityPresenter
import ua.willeco.clicon.mvp.presenter.FacilitiesPresenter
import ua.willeco.clicon.mvp.presenter.SplashActivityPresenter
import ua.willeco.clicon.mvp.view.BaseView
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class),(ApiServiceModule::class),(SharedPreferencesModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified SplashPresenter.
     * @param splashActivityPresenter SplashPresenter in which to inject the dependencies
     */
    fun inject(splashActivityPresenter: SplashActivityPresenter)
    fun inject(authorizationActivityPresenter: AuthorizationActivityPresenter)
    fun inject(facilityPresenter:FacilitiesPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: ApiServiceModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun sharedPreferModule(sharedPreferences: SharedPreferencesModule):Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}