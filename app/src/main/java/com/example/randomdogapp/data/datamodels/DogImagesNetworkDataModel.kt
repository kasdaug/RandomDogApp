package com.example.randomdogapp.data.datamodels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogImagesNetworkDataModel (
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String?
    )