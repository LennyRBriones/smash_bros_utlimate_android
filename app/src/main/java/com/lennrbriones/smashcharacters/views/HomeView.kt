package com.lennrbriones.smashcharacters.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.lennrbriones.smashcharacters.components.CardCharacter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lennrbriones.smashcharacters.R
import com.lennrbriones.smashcharacters.components.MainTopBar
import com.lennrbriones.smashcharacters.utils.Constants.Companion.CUSTOM_BLACK
import com.lennrbriones.smashcharacters.viewmodels.CharactersViewModel

@Composable
fun HomeView(viewModel: CharactersViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            MainTopBar(title = "Smash Bros Fighters", onClickBackButton = {}) {
                navController.navigate("SearchCharacterView")
            }
        }
    ) {
        ContentHomeView(viewModel, it, navController)
    }

}

@Composable
fun ContentHomeView(
    viewModel: CharactersViewModel,
    padding: PaddingValues,
    navController: NavController
) {
    val characters by viewModel.characters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color(CUSTOM_BLACK))
    ) {
        if (isLoading) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = stringResource(id = R.string.loading), color = Color.White)
            }
        } else {
            LazyColumn {
                items(characters) { item ->
                    CardCharacter(item) {
                        val mappedFighterNumber = mapFighterNumber(item.fighterNumber)
                        navController.navigate("CharacterView/${mappedFighterNumber}")
                    }
                }
            }
        }
    }
}

fun mapFighterNumber(fighterNumber: String): String {
    return fighterNumber.replace("ᵋ", "e")
}