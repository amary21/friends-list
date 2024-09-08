package com.amary.friends.domain

import com.amary.friends.core.remote.ResultState
import com.amary.friends.data.api.model.Friends
import com.amary.friends.data.api.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FriendsUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    operator fun invoke(): Flow<ResultState<List<Friends>>> {
        return dataRepository.getData()
    }
}