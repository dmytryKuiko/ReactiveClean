package com.example.dimi.reactiveclean.presentation.main.presenter.drawer

import com.example.dimi.reactiveclean.models.MenuItem
import com.example.dimi.reactiveclean.presentation.BaseDataPresenter

interface DrawerPresenter : BaseDataPresenter<MenuItem> {

    fun menuItemClicked(item: MenuItem)
}