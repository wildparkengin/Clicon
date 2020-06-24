package ua.willeco.clicon.di.component

import dagger.BindsInstance
import dagger.Component
import ua.willeco.clicon.di.module.ApiServiceModule
import ua.willeco.clicon.mvp.presenter.*
import ua.willeco.clicon.mvp.view.BaseView
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiServiceModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified SplashPresenter.
     * @param splashActivityPresenter SplashPresenter in which to inject the dependencies
     */
    fun inject(splashActivityPresenter: SplashActivityPresenter)
    fun inject(authorizationActivityPresenter: AuthorizationActivityPresenter)
    fun inject(homePresenter:HomeFragmentPresenter)
    fun inject(facilityDialogPresenter:FacilityDialogPresenter)
    fun inject(deleteDialogPresenter:DeleteDialogPresenter)
    fun inject(deviceDialogPresenter:DeviceDialogPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: ApiServiceModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}