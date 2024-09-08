package com.amary.friends.detail.screen

import androidx.lifecycle.ViewModel
import com.amary.friends.data.api.model.Friends
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun updateState(friends: Friends?) {
        if (friends == null) return
        val name = friends.name.split(" ")
        val firstName = name.first()
        val lastName = name.drop(1).joinToString(" ")
        val address = "${friends.addressNo} ${friends.street} ${friends.city} ${friends.zipCode} ${friends.country}"

        _state.update {
            it.copy(
                firstName = firstName,
                lastName = lastName,
                address = address,
                avatar = friends.avatar
            )
        }
    }
}