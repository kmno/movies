package com.movies.utils.di.modules

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import com.movies.application.MyApplication
import com.movies.utils.di.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * A module for Android-specific dependencies which require a [Context] or
 * [android.app.Application] to create.
 */
@Module
class AndroidModule {
    /**
     * Allow the application context to be injected but require that it be annotated with
     * [@Annotation][ForApplication] to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    //@ForApplication
    fun provideApplicationContext(application: MyApplication): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideLocationManager(application: MyApplication): LocationManager {
        return application.getSystemService(LOCATION_SERVICE) as LocationManager
    }
}