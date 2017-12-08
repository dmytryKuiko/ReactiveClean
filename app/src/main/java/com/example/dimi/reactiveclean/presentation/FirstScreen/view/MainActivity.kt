package com.example.dimi.reactiveclean.presentation.FirstScreen.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.presentation.FirstScreen.view_model.FirstScreenViewModelImpl
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FirstScreenViewModelImpl::class.java)

        viewModel.apply {
            provideData().observe(this@MainActivity, android.arch.lifecycle.Observer { articleList ->
                val list = articleList
            })
            provideError().observe(this@MainActivity, android.arch.lifecycle.Observer { error ->
                val text = error?.text
            })
        }
    }
}
