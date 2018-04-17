package com.movies.utils.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.movies.utils.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilderModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}