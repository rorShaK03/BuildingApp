package ru.hse.buildingapp.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ProjectRequestModel( val name : String,
                                val email: String,
                                val number : String,
                                val projectName : String,
                                val typeProject : String,
                                val totalArea : Int,
                                val budget : Int,
                                val city : String
                                )