package ru.hse.buildingapp.network.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PortfolioProjectModel(val id : Int,
                                 @SerialName("interior_name")
                                 val interiorName : String,
                                 val interiorPicture : String)
