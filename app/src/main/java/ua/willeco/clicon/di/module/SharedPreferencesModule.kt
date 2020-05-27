package ua.willeco.clicon.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ua.willeco.clicon.utility.Constants
import javax.inject.Singleton

@Module
object SharedPreferencesModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences{
        return context.getSharedPreferences(Constants.PROPERTY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }
}