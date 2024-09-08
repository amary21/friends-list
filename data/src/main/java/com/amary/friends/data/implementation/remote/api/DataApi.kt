package com.amary.friends.data.implementation.remote.api

import com.amary.friends.data.implementation.remote.response.FriendsResponse
import retrofit2.Response
import retrofit2.http.GET

interface DataApi {
    @GET("getData/test")
    suspend fun getData(): Response<List<FriendsResponse>>
}