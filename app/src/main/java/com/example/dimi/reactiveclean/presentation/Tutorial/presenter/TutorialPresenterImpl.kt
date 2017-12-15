package com.example.dimi.reactiveclean.presentation.Tutorial.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.Navigator.Tutorial.TutorialNavigator
import com.example.dimi.reactiveclean.presentation.Tutorial.view.ImageType

class TutorialPresenterImpl(private val navigator: TutorialNavigator, imageType: ImageType) : TutorialPresenter {

    private var imageTypeLiveData = MutableLiveData<ImageType>()

    init {
        imageTypeLiveData.value = imageType
    }

    override fun getImageType(): LiveData<ImageType> = imageTypeLiveData

    override fun clickNext() {
        navigator.wizardNext()
    }

    override fun clickBack() {
        navigator.wizardBack()
    }
}