package ru.hse.buildingapp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.hse.buildingapp.R

@Serializable
data class NewsModel(val id: Int,
                     val text: String,
                     val title: String = "Test title",
                     @SerialName("no_parse")
                     val iconId: Int = R.drawable.news_dcts,
                     val date: String = "")