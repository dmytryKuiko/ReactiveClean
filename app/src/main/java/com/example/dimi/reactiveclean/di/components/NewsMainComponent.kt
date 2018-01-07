package com.example.dimi.reactiveclean.di.components

import com.example.dimi.reactiveclean.base.BaseComponent
import com.example.dimi.reactiveclean.di.modules.NewsMainModule
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.presentation.NewsMain.view.NewsMainActivity
import com.example.dimi.reactiveclean.presentation.NewsMain.view.content.ContentFragment
import com.example.dimi.reactiveclean.presentation.NewsMain.view.section.SectionFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [NewsMainModule::class])
interface NewsMainComponent : BaseComponent<NewsMainActivity> {

    @Subcomponent.Builder
    interface Builder {
        fun build(): NewsMainComponent
    }

    fun inject(fragment: ContentFragment)
    fun inject(fragment: SectionFragment)
}