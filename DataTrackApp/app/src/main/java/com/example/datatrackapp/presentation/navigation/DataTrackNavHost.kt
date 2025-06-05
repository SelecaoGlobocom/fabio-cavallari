package com.example.datatrackapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.datatrackapp.presentation.screen.FilmChannelScreen
import com.example.datatrackapp.presentation.screen.HomeScreen
import com.example.datatrackapp.presentation.screen.MusicChannelScreen
import com.example.datatrackapp.presentation.screen.SportsChannelScreen

@Composable
fun DataTrackNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.HomeScreenRoute
    ) {
        composable<ScreenRoute.HomeScreenRoute> {
            HomeScreen(navController = navHostController)
        }
        composable<ScreenRoute.SportsChannelScreenRoute> { argument ->
            val sportsChannelScreenRoute = argument.toRoute<ScreenRoute.SportsChannelScreenRoute>()
            SportsChannelScreen(sportsChannelScreenRoute.title)
        }
        composable<ScreenRoute.FilmChannelScreenRoute> { argument ->
            val filmChannelScreenRoute = argument.toRoute<ScreenRoute.FilmChannelScreenRoute>()
            FilmChannelScreen(filmChannelScreenRoute.title)
        }
        composable<ScreenRoute.MusicChannelScreenRoute> { argument ->
            val musicChannelScreenRoute = argument.toRoute<ScreenRoute.MusicChannelScreenRoute>()
            MusicChannelScreen(musicChannelScreenRoute.title)
        }
    }
}