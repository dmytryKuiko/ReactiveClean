package com.example.dimi.reactiveclean.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        //New initialization disabled
        //AndroidInjection.inject(this)

        initDagger()
        super.onCreate(savedInstanceState)
    }
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    abstract fun initDagger()
}
