package ua.willeco.clicon.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import ua.willeco.clicon.mvp.contract.AuthContract
import ua.willeco.clicon.mvp.presenter.AuthPresenter

@Module
class AuthActivityModule (private var activity: Activity){
    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): AuthContract.Presenter {
        return AuthPresenter()
    }
}