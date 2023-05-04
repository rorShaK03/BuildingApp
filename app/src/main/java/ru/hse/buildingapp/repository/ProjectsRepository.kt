package ru.hse.buildingapp.repository

import ru.hse.buildingapp.network.models.ProjectModel
import ru.hse.buildingapp.network.models.ProjectStatus

object ProjectsRepository {
    var projects : MutableMap<Int, ProjectModel> = mutableMapOf()
        private set


    suspend fun updateData() {
        projects[0] = ProjectModel(0,
            "Sample project name 1",
            99999,
            6666,
            "Москва, улица Васи Пупкина",
            ProjectStatus.PREPARING)
        projects[1] = ProjectModel(1,
            "Sample project name 2",
            534534,
            63456,
            "Санкт-Петербург, улица Пупкина Василия Валентиновича",
            ProjectStatus.ON_PROGRESS)
    }
}