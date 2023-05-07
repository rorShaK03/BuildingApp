package ru.hse.buildingapp.network.authmodels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credentials (
    @SerialName("email")
    val login : String = "",
    val password : String = ""
)