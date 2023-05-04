package ru.hse.buildingapp.repository

import ru.hse.buildingapp.R

object PortfolioImagesRepository {
    var architecturalImgIds : List<Int> = listOf()
        private set
    var interiorImgIds : List<Int> = listOf()
        private set
    var consultingImgIds : List<Int> = listOf()
        private set
    var realEstateImgIds : List<Int> = listOf()
        private set

    suspend fun updateData() {
        architecturalImgIds = listOf(R.drawable.house1, R.drawable.house2, R.drawable.house3,
            R.drawable.house3, R.drawable.house4, R.drawable.house5, R.drawable.house6)
        interiorImgIds = listOf(R.drawable.interior1, R.drawable.interior2, R.drawable.interior3,
            R.drawable.interior4, R.drawable.interior5, R.drawable.interior6)
        consultingImgIds = listOf(R.drawable.consultings1, R.drawable.consultings2, R.drawable.consultings3,
            R.drawable.consultings4, R.drawable.consultings5, R.drawable.consultings6)
        realEstateImgIds = listOf(R.drawable.real_estate1, R.drawable.real_estate2, R.drawable.real_estate3,
            R.drawable.real_estate4, R.drawable.real_estate5, R.drawable.real_estate6)
    }
}