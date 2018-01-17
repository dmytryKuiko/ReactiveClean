package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.di.TempComponent
import com.example.dimi.reactiveclean.di.modules.SectionChosenModule
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.models.section.SectionChosenModel
import com.example.dimi.reactiveclean.presentation.main.view.sectionChosen.SectionChosenFragment
import dagger.BindsInstance
import dagger.Subcomponent

@CustomScope
@Subcomponent(modules = [SectionChosenModule::class])
interface SectionChosenComponent : TempComponent {

    @Subcomponent.Builder
    interface Builder {

        @CustomScope
        @BindsInstance
        fun sectionChosenModel(model: SectionChosenModel): Builder

        fun build(): SectionChosenComponent
    }

    fun inject(fragment: SectionChosenFragment)
}