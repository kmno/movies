package com.movies.application

import com.movies.utils.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }


    /*   lateinit var component : ApplicationComponent

       override fun onCreate() {
           super.onCreate()
           this.component = initDaggerComponent()
       }

       private fun initDaggerComponent(): ApplicationComponent {
           return DaggerApplicationComponent
                   .builder()
                   .androidModule(AndroidModule(this))
                   .netModule(NetModule(this))
                   .sharedPrefModule(SharedPrefModule(this))
                   .build()
       }*/
}