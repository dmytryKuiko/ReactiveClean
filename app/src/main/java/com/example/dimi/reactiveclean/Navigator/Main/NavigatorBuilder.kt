package com.example.dimi.reactiveclean.Navigator.Main

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import ru.terrakok.cicerone.android.SupportAppNavigator


class NavigatorBuilder {
    fun buildSupportAppNavigator(activity: FragmentActivity, containerId: Int,
                                 screenKeyActivity: String? = null, dataActivity: Any? = null,
                                 screenKeyFragment: String? = null, dataFragment: Any? = null) : SupportAppNavigator {
        return object: SupportAppNavigator(activity, containerId) {
            override fun createActivityIntent(screenKey: String?, data: Any?): Intent {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }
}