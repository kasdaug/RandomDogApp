package com.example.randomdogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.randomdogapp.ui.theme.RandomDogAppTheme
import com.example.randomdogapp.ui.viewmodels.DogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.net.URL

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: DogViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                println("Current viewModel String is: " + viewModel._uiState.newRandomDogImage)
                viewModel.getRandomDogImage(false)
                if(viewModel.uiState.newRandomDogImage != "test"){

                }
                println("Changed image name to: " + viewModel.uiState.newRandomDogImage)
                }
            }

        setContent {
            RandomDogAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    DogImage(url = URL("https://upload.wikimedia.org/wikipedia/commons/6/65/No-Image-Placeholder.svg"), description = "test")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DogImage(url:URL, description:String){
    GlideImage(
        model = url,
        contentDescription = description
    )
}

@Preview(showBackground = true)
@Composable
fun RandomDogImage() {
    RandomDogAppTheme {
        Greeting("Android")
    }
}