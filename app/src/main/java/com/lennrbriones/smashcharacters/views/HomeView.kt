package com.lennrbriones.smashcharacters.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.lennrbriones.smashcharacters.components.CardCharacter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.lennrbriones.smashcharacters.components.MainTopBar
import com.lennrbriones.smashcharacters.utils.Constants.Companion.CUSTOM_BLACK
import com.lennrbriones.smashcharacters.viewmodels.CharactersViewModel

@Composable
fun HomeView(viewModel: CharactersViewModel, navController: NavController){
    Scaffold(
        topBar = {
            MainTopBar(title = "Testing UI", onClickBackButton = {}){

            }
        }
    ) {
        ContentHomeView(viewModel, it, navController)
    }

}

@Composable
fun ContentHomeView(viewModel: CharactersViewModel, padding: PaddingValues, navController: NavController){
    val characters by viewModel.characters.collectAsState()
    LazyColumn (modifier = Modifier
        .padding(padding)
        .background(Color(CUSTOM_BLACK))
    ) {
        items(characters){item ->
            CardCharacter(item) {
                val mappedFighterNumber = mapFighterNumber(item.fighterNumber)
                navController.navigate("CharacterView/${mappedFighterNumber}")
                Log.d("Navigation", "Navigating to CharacterView with fighterNumber: ${item.fighterNumber}")

            }
        }
    }
}

fun mapFighterNumber(fighterNumber: String): String {
    return fighterNumber.replace("ᵋ", "e")
}