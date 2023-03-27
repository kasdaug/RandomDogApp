package com.example.randomdogapp.data.source

import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

sealed class Result<out String>{
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: String) : Result<Nothing>()
}

class DogImagesNetworkDataSource @Inject constructor() : DogImagesDataSource {
    private val randomDogAPIURL = URL("https://dog.ceo/api/breeds/image/random")

    override suspend fun fetchRandomDogImage(): Result<String> {
        //try{
        var test = ""
        println("Hitting FetchRandomDogImage")
        (randomDogAPIURL.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Content-Type", "application/json; utf-8")
            setRequestProperty("Accept", "application/json")
            doOutput = false
            println("Received answer from URL: ")
            inputStream.bufferedReader().use{
                it.lines().forEach{line ->
                    println(line)
                }
            }
            //outputStream.write(test)\
            return Result.Success(inputStream.toString())
//                inputStream.bufferedReader().use{
//                    it.lines().forEach(::println)
//                    if(it.readLine().contains("message")){
//                        test = it.readLine()
//                    }
//                }

            //}
            //val imageData = randomDogAPIURL.readBytes()
//        }catch(Exception:Exception){
//            println(Exception.message)
//            return Result.Error("Failed to open HttpURLConnection: ")
//        }

        }
        return Result.Error("Failed to open HttpURLConnection: ")
    }
}