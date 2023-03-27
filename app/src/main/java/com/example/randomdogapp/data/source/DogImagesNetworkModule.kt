package com.example.randomdogapp.data.source

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(
    ActivityComponent::class)
class DogImagesNetworkModule {

    @ActivityScoped
    @Provides
    fun networkModuleProvider() : DogImagesDataSource{
        return DogImagesNetworkDataSource()
    }
}