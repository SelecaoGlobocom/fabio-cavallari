package com.example.datatrackapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.datatrackapp.presentation.screen.FilmChanelScreen
import com.example.datatrackapp.presentation.screen.HomeScreen
import com.example.datatrackapp.presentation.screen.MusicChanelScreen
import com.example.datatrackapp.presentation.screen.SportsChanelScreen

@Composable
fun DataTrackNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.HomeScreenRoute
    ) {
        composable<ScreenRoute.HomeScreenRoute> {
            HomeScreen(navController = navHostController)
        }
        composable<ScreenRoute.SportsChanelScreenRoute> {
            SportsChanelScreen()
        }
        composable<ScreenRoute.FilmChanelScreenRoute> {
            FilmChanelScreen()
        }
        composable<ScreenRoute.MusicChanelScreenRoute> {
            MusicChanelScreen()
        }
    }
}