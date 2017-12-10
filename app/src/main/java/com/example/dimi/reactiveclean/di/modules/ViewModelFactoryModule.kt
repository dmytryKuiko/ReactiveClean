package com.example.dimi.reactiveclean.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.example.dimi.reactiveclean.di.scopes.FirstScreen
import com.example.dimi.reactiveclean.di.scopes.ViewModelKey
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem
import com.example.dimi.reactiveclean.presentation.FirstScreen.presenter.FirstScreenViewModel
import com.example.dimi.reactiveclean.presentation.FirstScreen.presenter.FirstScreenViewModelImpl
import com.example.dimi.reactiveclean.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {


//    @Binds
//    @Singleton
//    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}