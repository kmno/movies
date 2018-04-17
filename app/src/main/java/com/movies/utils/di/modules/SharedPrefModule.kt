package com.movies.utils.di.modules

import android.content.SharedPreferences
import com.movies.application.MyApplication
import com.movies.utils.PreferenceHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(): PreferenceHelper {
        return PreferenceHelper
    }

    @Provides
    @Singleton
    fun providesDefaultSharedPreferences(application: MyApplication): SharedPreferences {
        return PreferenceHelper.defaultPrefs(application)
    }
}
