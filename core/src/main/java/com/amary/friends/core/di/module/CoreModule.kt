package com.amary.friends.core.di.module

import android.content.Context
import com.amary.friends.core.coroutine.IoDispatcher
import com.amary.friends.core.network.NetworkConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoreModule {

    @Provides
    fun provideMoshi(): Moshi {
        return NetworkConfig.provideMoshi()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext context: Context,
        moshi: Moshi,
    ): Retrofit {
        return NetworkConfig.provideRetrofit(
            context = context,
            moshi = moshi,
        )
    }


    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}