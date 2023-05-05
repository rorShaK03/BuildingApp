package ru.hse.buildingapp.network.models

import kotlinx.serialization.Serializable
import ru.hse.buildingapp.R

enum class ProjectStatus {
    PREPARING, ON_PROGRESS, DONE
}

@Serializable
data class ProjectModel(val id : Int,
                        val projectName : String,
                        val area : Int,
                        val budget : Int,
                        val address : String,
                        val status : ProjectStatus = ProjectStatus.ON_PROGRESS,
                        val projectImageId: Int = R.drawable.project_img_sample,
                        val iconId: Int = R.drawable.projects_screen_house_icon){}