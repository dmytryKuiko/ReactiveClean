package com.example.dimi.reactiveclean.presentation.FirstScreen.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.App
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem
import com.example.dimi.reactiveclean.presentation.FirstScreen.presenter.FirstScreenPresenter
import com.squareup.leakcanary.RefWatcher
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), FirstScreenView {
    // @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject lateinit var refWatcher: RefWatcher

    @Inject lateinit var presenter: FirstScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.bindView(this)

        refresh_button.setOnClickListener { _ -> presenter.onRefreshClicked() }

//        val viewModel = ViewModelProviders.of(this, viewModelFactory)
//                .get(FirstScreenViewModelImpl::class.java)

        //viewModel.apply {
//            viewModel.getData().observe(this, Observer { articleList ->
//                val list = articleList
//                val a = 3
//                var b = 2
//                b++
//            })
//        viewModel.getError().observe(this, Observer { error ->
//                val text = error?.text
//                var b = 2
//                b++
//            })
////
//        viewModel.getProgress().observe(this, Observer { loading ->
//                loading?.let {
//                   update(it)
//                }
//            })
////
//        viewModel.getError().observe(this, Observer {
//                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
//            })
//        //}
//
        refWatcher.watch(presenter)
    }

    override fun initDagger() {
        (application as App).getMainActivityComponent().inject(this)
    }

    override fun updateModel(model: List<ArticleDisplayableItem>) {
        val list = listOf(model)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    override fun disableRefreshButton() {
        refresh_button.isEnabled = false
    }

    override fun enableRefreshButton() {
        refresh_button.isEnabled = true
    }

    override fun showDataSynchronized() {
        Toast.makeText(this, "Synchronized", Toast.LENGTH_LONG).show()
    }

    override fun finish() {
        super.finish()
        presenter.clearSubscriptions()

        (application as App).releaseMainActivityComponent()
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }
}
