package com.example.dimi.reactiveclean.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.dimi.reactiveclean.di.scopes.ViewModelKey
import com.example.dimi.reactiveclean.presentation.FirstScreen.view_model.FirstScreenViewModelImpl
import com.example.dimi.reactiveclean.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(FirstScreenViewModelImpl::class)
    abstract fun bindMainViewModel(firstScreenViewModelImpl: FirstScreenViewModelImpl): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}