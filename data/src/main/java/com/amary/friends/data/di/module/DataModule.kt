package com.amary.friends.data.di.module

import com.amary.friends.core.coroutine.IoDispatcher
import com.amary.friends.data.api.repository.DataRepository
import com.amary.friends.data.implementation.remote.api.DataApi
import com.amary.friends.data.implementation.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Provides
    @Singleton
    fun provideDataApi(
        retrofit: Retrofit
    ): DataApi = retrofit.create(DataApi::class.java)

    @Provides
    @Singleton
    fun provideDataRepository(
        dataApi: DataApi,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): DataRepository = DataRepositoryImpl(dataApi, dispatcher)
}