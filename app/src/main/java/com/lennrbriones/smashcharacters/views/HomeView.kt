package com.lennrbriones.smashcharacters.views

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
import com.lennrbriones.smashcharacters.components.MainTopBar
import com.lennrbriones.smashcharacters.utils.Constants.Companion.CUSTOM_BLACK
import com.lennrbriones.smashcharacters.viewmodels.CharactersViewModel

@Composable
fun HomeView(viewModel: CharactersViewModel){
    Scaffold(
        topBar = {
            MainTopBar(title = "Testing UI", onClickBackButton = {}){

            }
        }
    ) {
        ContentHomeView(viewModel, it)
    }

}

@Composable
fun ContentHomeView(viewModel: CharactersViewModel, padding: PaddingValues){
    val characters by viewModel.characters.collectAsState()
    LazyColumn (modifier = Modifier
        .padding(padding)
        .background(Color(CUSTOM_BLACK))
    ) {
        items(characters){item ->
            CardCharacter(item) {
            }
        }
    }
}