package com.example.randomdogapp.data.source

import com.example.randomdogapp.data.datamodels.DogImagesNetworkDataModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

//sealed class Result<out String>{
//    data class Success<out T>(val data: T) : Result<T>()
//    data class Error(val exception: String) : Result<Nothing>()
//}

class DogImagesNetworkDataSource @Inject constructor() : DogImagesDataSource {
    private val randomDogAPIURL = URL("https://dog.ceo/api/breeds/image/random")

    override suspend fun fetchRandomDogImage(): String{ //Result<T>
        //try{
        var test = ""
        println("Hitting FetchRandomDogImage")
        (randomDogAPIURL.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = false
            var result = ""

            println("Received answer from URL: ")
            inputStream.bufferedReader().use{
                it.lines().forEach{line ->
                    val dataAsString:DogImagesNetworkDataModel = Json.decodeFromString(line)
                    val imageURL:String = dataAsString.message
                    println(imageURL)
                    result = imageURL
                }
            }

            return result //Result.Success(

//        }catch(Exception:Exception){
//            println(Exception.message)
//            return Result.Error("Failed to open HttpURLConnection: ")
//        }

        }
        return "error" //Result.Error("Failed to open HttpURLConnection: ")
    }
}