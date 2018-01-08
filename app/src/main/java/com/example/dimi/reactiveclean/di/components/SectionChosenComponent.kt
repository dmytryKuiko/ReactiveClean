package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.base.TempComponent
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.modules.SectionChosenModule
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.presentation.NewsMain.view.section_chosen.SectionChosenFragment
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@CustomScope
@Subcomponent(modules = [SectionChosenModule::class])
interface SectionChosenComponent : TempComponent {

    @Subcomponent.Builder
    interface Builder {

        @CustomScope
        @BindsInstance
        fun sectionUrl(@Named(DiConstants.SECTION_CHOSEN_URL) url: String): Builder

        fun build(): SectionChosenComponent
    }

    fun inject(fragment: SectionChosenFragment)
}