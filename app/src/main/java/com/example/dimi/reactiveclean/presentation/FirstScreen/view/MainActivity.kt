package com.example.dimi.reactiveclean.presentation.FirstScreen.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.App
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem
import com.example.dimi.reactiveclean.presentation.FirstScreen.presenter.FirstScreenPresenter
import com.example.dimi.reactiveclean.presentation.FirstScreen.presenter.FirstScreenViewModel
import com.example.dimi.reactiveclean.presentation.FirstScreen.presenter.FirstScreenViewModelImpl
import com.squareup.leakcanary.RefWatcher
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject lateinit var refWatcher: RefWatcher

    //can inject directly viewModel because we are controlling the lifecycle of a FirstScreenModule
    //@Inject lateinit var viewM: FirstScreenViewModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FirstScreenViewModelImpl::class.java)

        viewModel.apply {
            getData().observe(this@MainActivity, Observer { articleList ->
                val list = articleList
                val a = 3
                var b = 2
                b++
            })
            getError().observe(this@MainActivity, Observer { _ ->
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
            })

            getSuccess().observe(this@MainActivity, Observer { _ ->
                Toast.makeText(this@MainActivity, "Synchronized", Toast.LENGTH_LONG).show()
            })

            getProgress().observe(this@MainActivity, Observer { state ->
                state?.let {
                    progressBar.visibility = state
                }

            })
        }

        refresh_button.setOnClickListener { _ -> viewModel.onRefreshClicked() }

        refWatcher.watch(viewModel)
    }

    override fun finish() {
        super.finish()
        (application as App).releaseMainActivityComponent()
    }

    override fun initDagger() {
        (application as App).getMainActivityComponent().inject(this)
    }
}
