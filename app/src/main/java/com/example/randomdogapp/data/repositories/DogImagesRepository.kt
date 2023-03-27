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
){ //:DogImagesRepositoryInterface
    suspend fun newRandomDogImage() : String{
       println("Hitting the Override function")
        return dogImagesDataSource.fetchRandomDogImage().toString() //Here we need to fetch result
    }
}

//@Module
//@InstallIn(ViewModelComponent::class)
//abstract class DogImagesModule{
//
//    @Binds
//    abstract fun bindDogImagesInterface(
//        dogImagesRepository:DogImagesRepository
//    ): DogImagesRepositoryInterface
//
//    @ViewModelScoped
//    @Provides
//    fun provideModule() : DogImagesRepositoryInterface {
//        return DogImagesRepository()
//    }
//    }
//}

