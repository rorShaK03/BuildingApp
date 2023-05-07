package ru.hse.buildingapp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hse.buildingapp.R

enum class ProjectStatus(val text : String) {
    PREPARING("Preparing"),
    ON_PROGRESS("On progress"),
    DONE("Done"),
    UNKNOWN("Unknown")
}

@Serializable
data class ProjectModel(val id : Int,
                        val projectName : String,
                        val area : Int,
                        @SerialName("price")
                        val budget : Int,
                        @SerialName("adress")
                        val address : String,
                        @SerialName("status")
                        private val status_ : String,
                        val projectImageId: Int = R.drawable.project_img_sample,
                        val iconId: Int = R.drawable.projects_screen_house_icon)
{
    val projStatus : ProjectStatus = when(status_) {
        "PREPARING" -> ProjectStatus.PREPARING
        "ON_PROGRESS" -> ProjectStatus.ON_PROGRESS
        "DONE" -> ProjectStatus.DONE
        else -> ProjectStatus.UNKNOWN
    }

}