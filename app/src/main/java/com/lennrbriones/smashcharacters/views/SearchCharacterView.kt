package com.lennrbriones.smashcharacters.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.lennrbriones.smashcharacters.utils.Constants
import com.lennrbriones.smashcharacters.viewmodels.CharactersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCharacterView(viewModel: CharactersViewModel, navController: NavController) {

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val character by viewModel.characters.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(Constants.CUSTOM_BLACK))
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            query = query,
            onQueryChange = { query = it },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            },
            trailingIcon = {
                Icon(imageVector = Icons.Default.Close, contentDescription = "",
                    modifier = Modifier.clickable { navController.popBackStack() })
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(Constants.CUSTOM_BLACK))
            ) {
            if (query.isNotEmpty()) {
                val filterCharacters = character.filter { it.name.contains(query, ignoreCase = true) }

                    LazyColumn {
                        items(filterCharacters) { char ->
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 10.dp, start = 10.dp, top = 10.dp)
                                    .clickable {
                                        navController.navigate(
                                            "CharacterView/${mapFighterNumber(char.fighterNumber)}"
                                        )
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(char.series.image)
                                            .decoderFactory(SvgDecoder.Factory())
                                            .build()
                                    ),
                                    contentDescription = "Logo",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(end = 10.dp)
                                )

                                Text(
                                    text = "${char.fighterNumber}   ${char.name}",
                                    fontSize = 20.sp,
                                    style = TextStyle(
                                        fontSize = 21.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Color.White,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}