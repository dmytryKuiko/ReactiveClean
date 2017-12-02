package com.example.dimi.reactiveclean.base


import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection

class BaseFragment : Fragment() {

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
