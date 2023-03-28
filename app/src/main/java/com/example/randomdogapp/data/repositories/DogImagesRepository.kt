package com.example.randomdogapp.data.repositories

import com.example.randomdogapp.data.source.DogImagesNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

//interface DogImagesRepositoryInterface{
//    suspend fun newRandomDogImage() : String
//}

@Singleton
class DogImagesRepository @Inject constructor(
    private val dogImagesDataSource:DogImagesNetworkDataSource
){
    suspend fun newRandomDogImage() : String{
       println("Hitting the Override function")
        val result = dogImagesDataSource.fetchRandomDogImage()
        println("Result is: " + result)
        return result//Here we need to fetch result
    }
}

