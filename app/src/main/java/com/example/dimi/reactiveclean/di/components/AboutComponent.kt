package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.di.TempComponent
import com.example.dimi.reactiveclean.di.modules.AboutModule
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.presentation.main.view.about.AboutFragment
import dagger.Subcomponent

@CustomScope
@Subcomponent(modules = [AboutModule::class])
interface AboutComponent : TempComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AboutComponent
    }

    fun inject(fragment: AboutFragment)
}