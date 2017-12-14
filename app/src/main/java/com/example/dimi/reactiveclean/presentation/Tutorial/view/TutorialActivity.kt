package com.example.dimi.reactiveclean.presentation.Tutorial.view

import android.os.Bundle
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.presentation.Tutorial.presenter.TutorialPresenter
import com.example.dimi.reactiveclean.presentation.Tutorial.presenter.TutorialPresenterImpl
import com.example.dimi.reactiveclean.utils.ComponentManager
import javax.inject.Inject
import javax.inject.Named

class TutorialActivity : BaseActivity() {

    @Inject
    @field:Named(DiConstants.TUTORIAL_SOURCE)
    lateinit var presenter: TutorialPresenter

    @Inject
    @field:Named(DiConstants.TUTORIAL_EVERYTHING)
    lateinit var presenter2: TutorialPresenter

    @Inject
    @field:Named(DiConstants.TUTORIAL_FAVOURITES)
    lateinit var presenter3: TutorialPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
    }

    override fun injectModule() {
        ComponentManager.getComponent(this).inject(this)
    }

    override fun releaseModule() {
        ComponentManager.releaseComponent(this)
    }
}
