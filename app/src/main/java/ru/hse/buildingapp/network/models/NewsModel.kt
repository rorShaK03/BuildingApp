package ru.hse.buildingapp.network.models

import kotlinx.serialization.Serializable
import ru.hse.buildingapp.R

@Serializable
data class NewsModel(val id: Int,
                     val text: String,
                     val title: String = "Test title",
                     val iconId: Int = R.drawable.news_dcts,
                     val date: String = "Oct - 2019") {}