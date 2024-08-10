package com.lennrbriones.smashcharacters.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lennrbriones.smashcharacters.viewmodels.CharactersViewModel
import com.lennrbriones.smashcharacters.views.CharacterView
import com.lennrbriones.smashcharacters.views.HomeView

@Composable
fun NavManager(viewModel: CharactersViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home" ) {
       composable ("Home"){
            HomeView(viewModel = viewModel,navController)
        }

        composable("CharacterView/{id}", arguments = listOf(
            navArgument("id"){type = NavType.StringType}
        )){

            val id = it.arguments?.getString("id") ?: 0 //initial value

            CharacterView(viewModel = viewModel,navController, id.toString())
        }

        composable("SearchCharacter"){

        }

    }
}