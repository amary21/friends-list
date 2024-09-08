package com.amary.friends.core.network

import android.content.Context
import com.amary.friends.core.BuildConfig
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

internal object NetworkConfig {
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun provideRetrofit(
        context: Context,
        moshi: Moshi,
    ): Retrofit {
        val collector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_WEEK
        )

        val okHttp = OkHttpClient.Builder()
            .connectTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            .cache(null)

        val okHttpBuild = okHttp
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(ChuckerInterceptor.Builder(context).collector(collector).build())
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpBuild)
            .build()
    }

    private const val RETROFIT_TIMEOUT: Long = 300
}