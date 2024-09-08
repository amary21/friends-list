package com.amary.friends.feature.list.screen

import com.amary.friends.data.api.model.Friends

internal data class ListState(
    val isLoading: Boolean = false,
    val friends: List<Friends> = emptyList(),
    val error: String? = null
)
