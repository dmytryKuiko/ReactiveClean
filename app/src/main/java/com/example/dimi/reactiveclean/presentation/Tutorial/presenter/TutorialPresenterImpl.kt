package com.example.dimi.reactiveclean.presentation.Tutorial.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.navigation.Tutorial.TutorialNavigator
import com.example.dimi.reactiveclean.presentation.Tutorial.view.ImageType

class TutorialPresenterImpl(private val navigator: TutorialNavigator, imageType: ImageType) : TutorialPresenter {

    private var imageTypeLiveData = MutableLiveData<ImageType>()

    init {
        imageTypeLiveData.value = imageType
    }

    override fun getData(): LiveData<ImageType> = imageTypeLiveData

    override fun disposeSubscriptions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clickNext() {
        navigator.navigateNext()
    }

    override fun clickBack() {
        navigator.navigateBack()
    }
}