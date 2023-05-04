package ru.hse.buildingapp.network.models

import kotlinx.serialization.Serializable

@Serializable
data class NewsModel(val id: Int, val text: String) {}