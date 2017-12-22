package com.example.dimi.reactiveclean.presentation.Main.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.presentation.Main.presenter.MainPresenter
import com.squareup.leakcanary.RefWatcher
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var refWatcher: RefWatcher

    @Inject lateinit var presenter: MainPresenter

//    @Inject lateinit var router: Router
//
//    @Inject lateinit var navigator: NavigatorHolder
//
//    @Inject lateinit var navBuilder: NavigatorBuilder

//    private val appNavigator: SupportAppNavigator by lazy {
//        navBuilder.buildSupportAppNavigator(this, R.id.refresh_button)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindData()
    }

    override fun onResume() {
        super.onResume()
       // navigator.setNavigator(appNavigator)

    }

    override fun onPause() {
        super.onPause()
        //navigator.removeNavigator()

    }

    override fun injectModule() {
        ComponentManager.getComponent(this).inject(this)
    }

    override fun releaseModule() {
        presenter.disposeSubscriptions()
        ComponentManager.releaseComponent(this)
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

        //exit_button.setOnClickListener { _ -> router.exit() }

        refWatcher.watch(presenter)
    }
}
