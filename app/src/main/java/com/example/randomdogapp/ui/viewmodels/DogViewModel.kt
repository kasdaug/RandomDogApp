package com.example.randomdogapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdogapp.data.repositories.DogImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogImagesRepository: DogImagesRepository //
) : ViewModel(){

    var _uiState by mutableStateOf(DogImagesUiState())
        private set
   val uiState:DogImagesUiState = _uiState

    fun getRandomDogImage(imageExists:Boolean){
        if(imageExists){
            DogImagesUiState(true,true,_uiState.newRandomDogImage)
        }
        else{
            DogImagesUiState(true,false,_uiState.newRandomDogImage)
        }
        viewModelScope.launch(Dispatchers.IO) {
            println("DEBUG: Fetching newRandomDogImage")
            DogImagesUiState(false, false, dogImagesRepository.newRandomDogImage())
            println("New uiState is: " + uiState.newRandomDogImage)
        }
    }

    data class DogImagesUiState(
        val isFetchingDogImage: Boolean = false,
        val isReplacingImage: Boolean = false,
        val newRandomDogImage: String = "test",
    )

}