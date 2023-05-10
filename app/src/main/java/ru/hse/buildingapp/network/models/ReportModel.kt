package ru.hse.buildingapp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportModel(val id : Int,
                       val title : String,
                       @SerialName("reportText")
                       val text : String)