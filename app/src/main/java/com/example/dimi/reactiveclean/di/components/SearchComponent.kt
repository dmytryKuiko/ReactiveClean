package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.di.TempComponent
import com.example.dimi.reactiveclean.di.modules.SearchModule
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.presentation.main.view.search.SearchFragment
import dagger.Subcomponent

@CustomScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent : TempComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): SearchComponent
    }

    fun inject(fragment: SearchFragment)
}