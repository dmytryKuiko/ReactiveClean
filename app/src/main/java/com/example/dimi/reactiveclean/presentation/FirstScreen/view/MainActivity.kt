package com.example.dimi.reactiveclean.presentation.FirstScreen.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.App
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.presentation.FirstScreen.presenter.FirstScreenPresenter
import com.squareup.leakcanary.RefWatcher
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var refWatcher: RefWatcher

    @Inject lateinit var presenter: FirstScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindData()
    }

    override fun injectModule() {
        (application as App).getMainActivityComponent(this).inject(this)
    }

    override fun releaseModule() {
        presenter.disposeSubscriptions()
        (application as App).releaseMainActivityComponent(this)
    }

    private fun bindData() {
        presenter.apply {
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

        refresh_button.setOnClickListener { _ -> presenter.onRefreshClicked() }

        refWatcher.watch(presenter)
    }
}
