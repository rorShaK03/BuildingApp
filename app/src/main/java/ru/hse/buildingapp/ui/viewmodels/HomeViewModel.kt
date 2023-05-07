package ru.hse.buildingapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.hse.buildingapp.repository.PortfolioImagesRepository

class HomeViewModelFactory(private val navController: NavHostController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(navController) as T
}

class HomeViewModel(val navController : NavHostController) : ViewModel() {
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