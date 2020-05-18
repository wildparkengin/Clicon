package ua.willeco.clicon.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(context: Context) {

    private var appContext:Context = context

    @Provides
    @Singleton
    fun provideApplication(): Context {
        return appContext
    }
}