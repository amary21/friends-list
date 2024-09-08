package com.amary.friends.feature.list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amary.friends.core.remote.ResultState
import com.amary.friends.domain.FriendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor(
    private val friendsUseCase: FriendsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ListState())
    val state = _state.asStateFlow()

    init {
        getList()
    }

    private fun getList() = viewModelScope.launch {
        friendsUseCase().collect { result ->
            _state.update {
                it.copy(
                    isLoading = result is ResultState.Loading,
                    friends = (result as? ResultState.Success)?.data.orEmpty(),
                    error = (result as? ResultState.Error)?.message
                )
            }
        }
    }
}