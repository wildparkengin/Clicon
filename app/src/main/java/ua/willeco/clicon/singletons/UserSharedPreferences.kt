package ua.willeco.clicon.singletons

import android.content.Context
import android.content.SharedPreferences
import ua.willeco.clicon.utility.Constants
import javax.inject.Singleton

@Singleton
object UserSharedPreferences {
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PROPERTY_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }
}