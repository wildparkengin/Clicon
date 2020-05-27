package ua.willeco.clicon.mvp.presenter

import ua.willeco.clicon.di.component.DaggerPresenterInjector
import ua.willeco.clicon.di.component.PresenterInjector
import ua.willeco.clicon.di.module.ApiServiceModule
import ua.willeco.clicon.di.module.ContextModule
import ua.willeco.clicon.di.module.SharedPreferencesModule
import ua.willeco.clicon.mvp.view.BaseView

abstract class BasePresenter<out V: BaseView>(protected val view:V) {

    /**
     * The inhector used to inject required dependencies
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(ApiServiceModule)
        .sharedPreferModule(SharedPreferencesModule)
        .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when(this){
            is SplashActivityPresenter ->{
                injector.inject(this)
            }
            is AuthorizationActivityPresenter ->{
                injector.inject(this)
            }
            is FacilitiesPresenter ->{
                injector.inject(this)
            }
        }
    }
}