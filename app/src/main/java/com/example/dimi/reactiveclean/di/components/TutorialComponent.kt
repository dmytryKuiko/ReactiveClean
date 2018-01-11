package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.di.BaseComponent
import com.example.dimi.reactiveclean.di.modules.TutorialModule
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.presentation.tutorial.view.*
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [TutorialModule::class])
interface TutorialComponent : BaseComponent<TutorialActivity> {

    @Subcomponent.Builder
    interface Builder {
        fun build(): TutorialComponent
    }

    fun inject(fragment: TutorialFirstFragment)

    fun inject(fragment: TutorialSecondFragment)

    fun inject(fragment: TutorialThirdFragment)
}