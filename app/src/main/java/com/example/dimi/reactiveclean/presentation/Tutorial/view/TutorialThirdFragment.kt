package com.example.dimi.reactiveclean.presentation.Tutorial.view

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.components.TutorialComponent
import com.example.dimi.reactiveclean.presentation.Tutorial.presenter.TutorialPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import javax.inject.Inject
import javax.inject.Named

class TutorialThirdFragment : TutorialFragment() {

    @Inject
    @field:Named(DiConstants.TUTORIAL_THIRD_SCREEN)
    override lateinit var presenter: TutorialPresenter

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? TutorialComponent) ?:
                throw ClassCastException("Component is not an instance of Tutorial Component")
        component.inject(this)
    }
}