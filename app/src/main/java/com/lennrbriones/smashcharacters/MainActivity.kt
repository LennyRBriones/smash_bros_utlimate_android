package com.lennrbriones.smashcharacters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lennrbriones.smashcharacters.navigation.NavManager
import com.lennrbriones.smashcharacters.ui.theme.SmashCharactersTheme
import com.lennrbriones.smashcharacters.viewmodels.CharactersViewModel
import com.lennrbriones.smashcharacters.views.HomeView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: CharactersViewModel by viewModels()
        setContent {
            SmashCharactersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(viewModel)
                }
            }
        }
        //enableEdgeToEdge()
    }
}