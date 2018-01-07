package com.example.dimi.reactiveclean.presentation.NewsMain.view.section_chosen


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.utils.ComponentManager


/**
 * A simple [Fragment] subclass.
 */
class SectionChosenFragment : Fragment() {

    override fun onAttach(context: Context?) {
        ComponentManager.getTempComponent(context!!, this, null)
        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section_chosen, container, false)
    }

}// Required empty public constructor
