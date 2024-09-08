package com.amary.friends.core.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T : Any, R : Any> apiResult(
    remoteApi: suspend () -> Response<T>,
    result: (T) -> R,
    dispatcher: CoroutineDispatcher,
): Flow<ResultState<R>> = flow {
    emit(ResultState.Loading)
    try {
        val response = remoteApi()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(ResultState.Success(data = result(body)))
        } else {
            emit(ResultState.Error(message = response.message()))
        }
    } catch (e: Exception) {
        emit(ResultState.Error(message = "Data Not Found"))
    }
}.flowOn(dispatcher)