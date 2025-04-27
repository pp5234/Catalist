package com.example.catalist.navigation

import BreedsDetailsScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catalist.cats.details.BreedsDetailsViewModel
import com.example.catalist.cats.list.BreedsListScreen
import com.example.catalist.cats.list.BreedsListViewModel


@Composable
fun BreedsNavigation () {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable(route = "list") {
            val viewModel = hiltViewModel<BreedsListViewModel>()
            BreedsListScreen(
                viewModel = viewModel,
                onBreedListClick = { breedId : String -> navController.navigate(route = "details/$breedId") }
            )
        }

        composable(
            route = "details/{breedId}",
            arguments = listOf(navArgument(name = "breedId") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }))
        {
            val viewModel = hiltViewModel<BreedsDetailsViewModel>()
            BreedsDetailsScreen(viewModel = viewModel)
        }

    }
}