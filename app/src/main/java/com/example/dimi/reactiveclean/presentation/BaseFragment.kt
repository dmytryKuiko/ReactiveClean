package com.example.dimi.reactiveclean.presentation


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    protected abstract val layoutId: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectModule(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(layoutId, container, false)

    protected abstract fun injectModule(context: Context)

    open fun onBackPressed() {}
}
