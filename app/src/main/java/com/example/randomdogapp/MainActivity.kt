package com.example.randomdogapp

import android.annotation.SuppressLint
import android.media.metrics.Event
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

import com.example.randomdogapp.ui.theme.RandomDogAppTheme
import com.example.randomdogapp.ui.viewmodels.DogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: DogViewModel by viewModels()
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                println("Current viewModel String is: " + viewModel.uiState)

                viewModel.getRandomDogImage(false)
                //RandomDogImage(viewModel.uiState.value.newRandomDogImage)
                }
            }

        setContent {
            RandomDogAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //RandomDogImage("https://images.dog.ceo/breeds/hound-blood/n02088466_9359.jpg")//viewModel.uiState.value.newRandomDogImage)
                    EventListener(viewModel)
                }
            }
        }
    }
}

@Composable
fun DogImage(url:String, description:String){
    println("Setting image with url: " + url)
    AsyncImage(url, description)
}


@Composable
fun RandomDogImage(url:String) {
    RandomDogAppTheme {
        DogImage(url, "test")
    }
}

@Composable
fun EventListener(
    dogViewModel:DogViewModel
){
    LaunchedEffect(Unit) {
        while(true) {
            dogViewModel.getRandomDogImage(true)
            delay(5000)
        }
    }

    val dogState = dogViewModel.uiState.collectAsState()
    if(dogState.value.isReplacingImage){
        RandomDogImage(dogState.value.newRandomDogImage)
    }
}