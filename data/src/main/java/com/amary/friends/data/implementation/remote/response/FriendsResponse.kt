package com.amary.friends.data.implementation.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FriendsResponse(
    @Json(name = "createdAt") val createdAt: String = "",
    @Json(name = "name") val name: String = "",
    @Json(name = "avatar") val avatar: String = "",
    @Json(name = "city") val city: String = "",
    @Json(name = "country") val country: String = "",
    @Json(name = "county") val county: String = "",
    @Json(name = "address_no") val addressNo: String = "",
    @Json(name = "street") val street: String = "",
    @Json(name = "zip_code") val zipCode: String = "",
    @Json(name = "id") val id: String = "",
)
