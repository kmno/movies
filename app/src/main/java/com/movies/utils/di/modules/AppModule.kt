package com.movies.utils.di.modules

import android.content.Context
import com.movies.application.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: MyApplication): Context {
        return application.applicationContext
    }
}