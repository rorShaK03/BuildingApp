package ru.hse.buildingapp.network.models

import kotlinx.serialization.Serializable

@Serializable
data class MeetingModel(val title: String,
                        val date: String)