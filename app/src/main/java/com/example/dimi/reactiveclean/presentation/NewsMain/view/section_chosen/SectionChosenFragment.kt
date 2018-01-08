package com.example.dimi.reactiveclean.presentation.NewsMain.view.section_chosen

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.di.components.SectionChosenComponent
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section_chosen.SectionChosenPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import javax.inject.Inject

class SectionChosenFragment : Fragment() {

    @Inject
    lateinit var presenter: SectionChosenPresenter

    override fun onAttach(context: Context?) {
        (ComponentManager.getTempComponent(context!!, this, null) as SectionChosenComponent).inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_section_chosen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(isRemoving) {
            ComponentManager.releaseTempComponent(this)
        }
    }
}
