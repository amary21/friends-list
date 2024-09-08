package com.amary.friends.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle

interface DetailNavigation {
    val argument: String
    fun getDetailActivity(context: Context, argument: Bundle): Intent
}