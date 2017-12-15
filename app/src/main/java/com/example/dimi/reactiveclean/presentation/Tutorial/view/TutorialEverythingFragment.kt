package com.example.dimi.reactiveclean.presentation.Tutorial.view

import android.content.Context
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.components.TutorialComponent
import com.example.dimi.reactiveclean.presentation.Tutorial.presenter.TutorialPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import javax.inject.Inject
import javax.inject.Named

class TutorialEverythingFragment : TutorialFragment() {

    @Inject
    @field:Named(DiConstants.TUTORIAL_EVERYTHING)
    override lateinit var presenter: TutorialPresenter

    override fun injectModule(context: Context) {
        val component = (ComponentManager.getComponent(context) as? TutorialComponent) ?:
                throw ClassCastException("Component is not an instance of Tutorial Component")
        component.inject(this)
    }
}