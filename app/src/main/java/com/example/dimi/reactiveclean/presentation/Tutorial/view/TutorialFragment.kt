package com.example.dimi.reactiveclean.presentation.tutorial.view


import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.BaseFragment
import com.example.dimi.reactiveclean.models.tutorial.ImageType
import com.example.dimi.reactiveclean.presentation.tutorial.presenter.TutorialPresenter
import kotlinx.android.synthetic.main.fragment_tutorial.*

abstract class TutorialFragment : BaseFragment() {

    abstract var presenter: TutorialPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getData().observe(this, Observer { imageType ->
            imageType?.let { showImage(imageType) }
        })
        tutorial_next_button.setOnClickListener { _ -> presenter.clickNext() }
        tutorial_back_button.setOnClickListener { _ -> presenter.clickBack() }
    }

    private fun showImage(image: ImageType) {
        when (image) {
            ImageType.SOURCE -> tutorial_image.setBackgroundColor(Color.GREEN)
            ImageType.EVERYTHING -> tutorial_image.setBackgroundColor(Color.GRAY)
            ImageType.FAVOURITES -> tutorial_image.setBackgroundColor(Color.YELLOW)
        }
    }
}
