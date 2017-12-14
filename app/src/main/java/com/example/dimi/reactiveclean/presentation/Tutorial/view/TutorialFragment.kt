package com.example.dimi.reactiveclean.presentation.Tutorial.view


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseFragment
import com.example.dimi.reactiveclean.utils.ComponentManager
import kotlinx.android.synthetic.main.fragment_tutorial.*

abstract class TutorialFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    fun showImage(image: ImageType) {
        when (image) {
            ImageType.SOURCE -> tutorial_image.setBackgroundColor(Color.GREEN)
            ImageType.EVERYTHING -> tutorial_image.setBackgroundColor(Color.GRAY)
            ImageType.FAVOURITES -> tutorial_image.setBackgroundColor(Color.YELLOW)
        }
    }
}
