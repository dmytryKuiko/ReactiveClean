package com.example.dimi.reactiveclean.presentation.NewsMain.view

import android.os.Bundle
import android.view.View
import com.example.dimi.reactiveclean.R
import com.example.dimi.reactiveclean.base.BaseActivity
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.NewsMainPresenter
import com.example.dimi.reactiveclean.utils.ComponentManager
import com.example.dimi.reactiveclean.utils.NavigatorExtensions.Navigators.ExtendedNavigator
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_news_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import javax.inject.Inject

class NewsMainActivity : BaseActivity(), View.OnClickListener {

    @Inject
    lateinit var presenter: NewsMainPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val appNavigator: SupportFragmentNavigator by lazy {
        ExtendedNavigator(this, R.id.news_main_activity_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_main)

        news_main_activity_content_button.setOnClickListener(this)
        news_main_activity_sections_button.setOnClickListener(this)
        presenter.listenField(RxTextView.textChangeEvents(news_main_activity_search_editText))
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(appNavigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun injectModule() {
        ComponentManager.getComponent(this).inject(this)
    }

    override fun releaseModule() {
        ComponentManager.releaseComponent(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            news_main_activity_content_button -> presenter.onContentClicked()
            news_main_activity_sections_button -> presenter.onSectionsClicked()
            else -> TODO()
        }
    }
}
