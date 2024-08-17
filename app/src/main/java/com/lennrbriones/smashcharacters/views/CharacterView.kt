package com.lennrbriones.smashcharacters.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lennrbriones.smashcharacters.R
import com.lennrbriones.smashcharacters.components.CharacterImage
import com.lennrbriones.smashcharacters.components.CharactersTopBar
import com.lennrbriones.smashcharacters.components.MainImage
import com.lennrbriones.smashcharacters.components.MainTopBar
import com.lennrbriones.smashcharacters.utils.Constants.Companion.CUSTOM_BLACK
import com.lennrbriones.smashcharacters.viewmodels.CharactersViewModel

@Composable
fun CharacterView(viewModel: CharactersViewModel, navController: NavController, id: String) {

    LaunchedEffect(Unit) {
        viewModel.getCharacterByNumber(id)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.cleanScreen()
        }
    }

    Scaffold(
        topBar = {
            CharactersTopBar(
                title = "# ${viewModel.state.fighterNumber}  ${viewModel.state.name}",
                showBackButton = true,
                logo = viewModel.state.seriesImage,
                onClickBackButton = { navController.popBackStack()})
        }
    ) {
        ContentCharacterView(it,viewModel)
    }
}

@Composable
fun ContentCharacterView(padding: PaddingValues, viewModel: CharactersViewModel) {
    val state = viewModel.state
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
            Column(
                modifier = Modifier
                    .background(Color(CUSTOM_BLACK))
                    .fillMaxHeight()
            ) {
                CharacterImage(imageUrl = state.fullImage)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 5.dp)
                ) {
                }
                val scroll = rememberScrollState(0)
                Text(
                    text = state.description,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                        .verticalScroll(scroll)
                )
            }
        }
    }
}