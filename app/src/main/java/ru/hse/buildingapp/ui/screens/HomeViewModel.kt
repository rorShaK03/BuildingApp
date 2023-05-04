package ru.hse.buildingapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.repository.PortfolioImagesRepository

class HomeViewModel : ViewModel() {
    var architecturalImgIds : List<Int> = listOf()
    var interiorImgIds : List<Int> = listOf()
    var consultingImgIds : List<Int> = listOf()
    var realEstateImgIds : List<Int> = listOf()

    init {
        viewModelScope.launch {
            PortfolioImagesRepository.updateData()
            architecturalImgIds = PortfolioImagesRepository.architecturalImgIds
            interiorImgIds = PortfolioImagesRepository.interiorImgIds
            consultingImgIds = PortfolioImagesRepository.consultingImgIds
            realEstateImgIds = PortfolioImagesRepository.realEstateImgIds
        }
    }
}