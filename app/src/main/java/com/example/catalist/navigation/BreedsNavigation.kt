package com.example.catalist.navigation

import BreedsDetailsScreenContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catalist.cats.list.BreedsListScreen

@Composable
fun BreedsNavigation () {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable(route = "list") {
            BreedsListScreen(
                onBreedListClick = { breedId -> navController.navigate(route = "details/$breedId") }
            )
        }

        composable(
            route = "details/{breedId}",
            arguments = listOf(navArgument(name = "breedId") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }))
        { NavBackStackEntry ->
            BreedsDetailsScreenContent()
        }

    }
}