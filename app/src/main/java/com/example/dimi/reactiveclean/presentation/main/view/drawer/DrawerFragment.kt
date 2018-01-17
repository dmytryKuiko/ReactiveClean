package com.example.dimi.reactiveclean.presentation.main.view.drawer


import android.content.Context
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.presentation.BaseFragment

class DrawerFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_drawer

    override fun injectModule(context: Context) {}
}
