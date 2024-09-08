package com.amary.friends.data.api.repository

import com.amary.friends.core.remote.ResultState
import com.amary.friends.data.api.model.Friends
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getData(): Flow<ResultState<List<Friends>>>
}