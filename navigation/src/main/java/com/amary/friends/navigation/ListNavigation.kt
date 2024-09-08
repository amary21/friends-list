package com.amary.friends.navigation

import android.content.Context
import android.content.Intent

interface ListNavigation {
    fun getListActivity(context: Context): Intent
}