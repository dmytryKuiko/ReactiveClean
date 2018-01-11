package com.example.dimi.reactiveclean.presentation


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        injectModule(context!!)
    }

    abstract fun injectModule(context: Context)
}
