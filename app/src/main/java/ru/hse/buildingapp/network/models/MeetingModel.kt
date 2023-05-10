package ru.hse.buildingapp.network.models

import kotlinx.serialization.Serializable

@Serializable
data class MeetingModel(val titleMeeting: String,
                        val date: String)