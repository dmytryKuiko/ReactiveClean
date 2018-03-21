package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.di.BaseComponent
import com.example.dimi.reactiveclean.di.modules.MainModule
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.presentation.main.view.MainActivity
import com.example.dimi.reactiveclean.presentation.main.view.MainFragment
import com.example.dimi.reactiveclean.presentation.main.view.content.ContentFragment
import com.example.dimi.reactiveclean.presentation.main.view.drawer.DrawerFragment
import com.example.dimi.reactiveclean.presentation.main.view.section.SectionFragment
import dagger.Subcomponent

/**
 * Component for MainActivity
 * @see MainActivity
 */
@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent : BaseComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }

    fun inject(fragment: MainFragment)

    fun inject(fragment: ContentFragment)

    fun inject(fragment: SectionFragment)

    fun inject(fragment: DrawerFragment)

    fun sectionChosenBuilder(): SectionChosenComponent.Builder

    fun searchBuilder(): SearchComponent.Builder

    fun aboutBuilder(): AboutComponent.Builder
}