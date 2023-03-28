package com.example.randomdogapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdogapp.data.repositories.DogImagesRepository
//import com.example.randomdogapp.data.source.Result
//import com.example.randomdogapp.data.source.Result.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogImagesRepository: DogImagesRepository //
) : ViewModel(){

    //var _uiState by MutableStateFlow() //DogImagesUiState()
    //    private set
   //val uiState:DogImagesUiState = _uiState

    private val _uiState = MutableStateFlow<DogImagesUiState>(DogImagesUiState())
    val uiState = _uiState.asStateFlow()
    fun getRandomDogImage(imageExists:Boolean){
        if(imageExists){
            DogImagesUiState(true,true, uiState.value.newRandomDogImage)
        }
        else{
            DogImagesUiState(true,false,uiState.value.newRandomDogImage)
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = dogImagesRepository.newRandomDogImage()
                println("Updating UIState with ReplacingImage")
                _uiState.update { DogImagesUiState(false,true,result)
                }

            //DogImagesUiState(false, false, dogImagesRepository.newRandomDogImage())
            //println("New uiState is: " + _uiState.newRandomDogImage)
        }

    }

    data class DogImagesUiState(
        val isFetchingDogImage: Boolean = false,
        val isReplacingImage: Boolean = false,
        val newRandomDogImage: String = "test",
    )

}