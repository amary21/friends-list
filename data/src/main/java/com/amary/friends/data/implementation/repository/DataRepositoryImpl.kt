package com.amary.friends.data.implementation.repository

import com.amary.friends.core.remote.ResultState
import com.amary.friends.core.remote.apiResult
import com.amary.friends.data.api.model.Friends
import com.amary.friends.data.api.repository.DataRepository
import com.amary.friends.data.implementation.mapper.toFriends
import com.amary.friends.data.implementation.remote.api.DataApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DataRepositoryImpl(
    private val dataApi: DataApi,
    private val dispatcher: CoroutineDispatcher
) : DataRepository {
    override fun getData(): Flow<ResultState<List<Friends>>> {
        return apiResult(
            remoteApi = { dataApi.getData() },
            dispatcher = dispatcher,
            result = { response ->
                response.map { it.toFriends() }
            }
        ).flowOn(dispatcher)
    }
}