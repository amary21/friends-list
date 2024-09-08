package com.amary.friends.data.implementation.mapper

import com.amary.friends.data.api.model.Friends
import com.amary.friends.data.implementation.remote.response.FriendsResponse

internal fun FriendsResponse.toFriends(): Friends {
    return Friends(
        createdAt = createdAt,
        name = name,
        avatar = avatar,
        city = city,
        country = country,
        county = county,
        addressNo = addressNo,
        street = street,
        zipCode = zipCode,
        id = id,
    )
}