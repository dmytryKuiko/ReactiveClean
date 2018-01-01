package com.example.dimi.reactiveclean.presentation.Tutorial.presenter

import com.example.dimi.reactiveclean.base.BaseDataPresenter
import com.example.dimi.reactiveclean.presentation.Tutorial.view.ImageType

interface TutorialPresenter : BaseDataPresenter<ImageType> {

    fun clickNext()

    fun clickBack()
}