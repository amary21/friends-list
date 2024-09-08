package com.amary.friends.list.di.module

import com.amary.friends.detail.navigation.DetailNavigationImpl
import com.amary.friends.feature.list.navigation.ListNavigationImpl
import com.amary.friends.navigation.DetailNavigation
import com.amary.friends.navigation.ListNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {
    @Binds
    fun bindListNavigation(impl: ListNavigationImpl): ListNavigation

    @Binds
    fun bindDetailNavigation(impl: DetailNavigationImpl): DetailNavigation

}