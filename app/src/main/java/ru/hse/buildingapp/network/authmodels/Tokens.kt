package ru.hse.buildingapp.network.authmodels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tokens (
    var token : String = "",
    var refreshToken : String = ""
    )

@Serializable
data class RefreshTokensAdapter (
    @SerialName("accessToken")
    var token : String = "",
    var refreshToken : String = ""
)