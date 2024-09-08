package com.amary.friends.core.remote

sealed interface ResultState<out T> {
    data object Loading : ResultState<Nothing>
    data class Error(val message: String) : ResultState<Nothing>
    data class Success<T>(val data: T) : ResultState<T>
}