package com.example.randomdogapp.data.source

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent


interface DogImagesDataSource {
    suspend fun fetchRandomDogImage(): String//Result<String>
}