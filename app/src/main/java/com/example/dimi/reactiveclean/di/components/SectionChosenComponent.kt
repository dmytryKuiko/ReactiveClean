package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.base.TempComponent
import com.example.dimi.reactiveclean.di.modules.SectionChosenModule
import com.example.dimi.reactiveclean.di.scopes.FragmentScope
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SectionChosenModule::class])
interface SectionChosenComponent : TempComponent {

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun sectionUrl(url: String): Builder

        fun build(): SectionChosenComponent
    }
}