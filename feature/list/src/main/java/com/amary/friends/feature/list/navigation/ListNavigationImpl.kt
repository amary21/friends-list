package com.amary.friends.feature.list.navigation

import android.content.Context
import android.content.Intent
import com.amary.friends.feature.list.screen.ListActivity
import com.amary.friends.navigation.ListNavigation
import javax.inject.Inject

class ListNavigationImpl @Inject constructor() : ListNavigation {
    override fun getListActivity(context: Context): Intent {
        return Intent(context, ListActivity::class.java)
    }
}