package com.amary.friends.data.api.model

import java.io.Serializable

data class Friends(
    val createdAt: String = "",
    val name: String = "",
    val avatar: String = "",
    val city: String = "",
    val country: String = "",
    val county: String = "",
    val addressNo: String = "",
    val street: String = "",
    val zipCode: String = "",
    val id: String = "",
) : Serializable