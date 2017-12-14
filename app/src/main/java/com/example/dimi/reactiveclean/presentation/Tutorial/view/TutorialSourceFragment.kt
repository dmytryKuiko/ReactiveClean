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

class TutorialSourceFragment : TutorialFragment() {

    @Inject
    @field:Named(DiConstants.TUTORIAL_SOURCE)
    lateinit var presenter: TutorialPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getImageType().observe(this, Observer { imageType ->
            imageType?.let { showImage(imageType) }
        })
    }

    override fun injectModule(context: Context) {
        (ComponentManager.getComponent(context) as? TutorialComponent)?.inject(this)
    }
}
