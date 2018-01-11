package com.example.dimi.reactiveclean.presentation.tutorial.presenter

import com.example.dimi.reactiveclean.presentation.BaseDataPresenter
import com.example.dimi.reactiveclean.models.tutorial.ImageType

interface TutorialPresenter : BaseDataPresenter<ImageType> {

    fun clickNext()

    fun clickBack()
}