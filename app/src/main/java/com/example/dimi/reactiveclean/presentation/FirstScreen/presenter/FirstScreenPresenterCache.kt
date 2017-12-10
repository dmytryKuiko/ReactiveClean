package com.example.dimi.reactiveclean.presentation.FirstScreen.presenter

import com.example.dimi.reactiveclean.models.ArticleDisplayableItem

class FirstScreenPresenterCache(var articleList: MutableList<ArticleDisplayableItem> = mutableListOf(),
                                var showProgress: Boolean = true)