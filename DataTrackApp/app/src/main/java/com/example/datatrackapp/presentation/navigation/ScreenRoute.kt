package com.example.datatrackapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute(
    val title: String,
) {
    @Serializable
    data object HomeScreenRoute : ScreenRoute("Home")
    @Serializable
    data object SportsChanelScreenRoute : ScreenRoute("Sports")
    @Serializable
    data object FilmChanelScreenRoute : ScreenRoute("Film")
    @Serializable
    data object MusicChanelScreenRoute : ScreenRoute("Music")
}