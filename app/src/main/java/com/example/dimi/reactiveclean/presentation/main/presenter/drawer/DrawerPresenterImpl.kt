package com.example.dimi.reactiveclean.presentation.main.presenter.drawer

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.dimi.reactiveclean.models.MenuItem
import com.example.dimi.reactiveclean.navigation.main.NewsMainNavigator
import com.example.dimi.reactiveclean.presentation.main.presenter.MenuController
import javax.inject.Inject

class DrawerPresenterImpl
@Inject constructor(
    private val menuController: MenuController,
    private val navigator: NewsMainNavigator
) : DrawerPresenter {

    private val selectedLiveData: MutableLiveData<MenuItem> = MutableLiveData()

    private var selectedItem: MenuItem = MenuItem.News
        set(newValue) {
            field = newValue
            selectedLiveData.value = newValue
        }

    init {
        selectedLiveData.value = MenuItem.News
    }

    override fun disposeSubscriptions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getData(): LiveData<MenuItem> = selectedLiveData

    override fun menuItemClicked(item: MenuItem) {
        if (item != selectedItem) {
            menuController.close()
            selectedItem = item
            navigator.setNewRoot(item)
        }

    }
}