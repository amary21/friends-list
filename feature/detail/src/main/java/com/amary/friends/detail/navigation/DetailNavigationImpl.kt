package com.amary.friends.detail.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.amary.friends.detail.screen.DetailActivity
import com.amary.friends.navigation.DetailNavigation
import javax.inject.Inject

class DetailNavigationImpl @Inject constructor() : DetailNavigation {
    override val argument: String
        get() = ARGUMENT

    override fun getDetailActivity(context: Context, argument: Bundle): Intent {
        return Intent(context, DetailActivity::class.java).apply {
            putExtras(argument)
        }
    }

    internal companion object {
        const val ARGUMENT = "argument"
    }
}