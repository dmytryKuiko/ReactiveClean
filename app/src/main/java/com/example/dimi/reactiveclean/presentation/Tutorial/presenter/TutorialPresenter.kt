package com.example.dimi.reactiveclean.presentation.Tutorial.presenter

import android.arch.lifecycle.LiveData
import com.example.dimi.reactiveclean.presentation.Tutorial.view.ImageType

interface TutorialPresenter {
    fun getImageType(): LiveData<ImageType>
}