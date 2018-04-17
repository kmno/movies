package com.movies.utils.di.components

import com.movies.application.MyApplication
import com.movies.utils.di.modules.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class,
            AndroidModule::class,
            NetModule::class,
            SharedPrefModule::class,
            ViewModelBuilderModule::class,
            MainActivityModule::class])

interface ApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()

    //fun inject(mainViewModel: AndroidViewModel)
    //fun inject(mainActivity: MainActivity)

}