package com.example.datatrackapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute(
    val title: String,
) {
    @Serializable
    data object HomeScreenRoute : ScreenRoute("Home")
    @Serializable
    data object SportsChannelScreenRoute : ScreenRoute("Sports")
    @Serializable
    data object FilmChannelScreenRoute : ScreenRoute("Film")
    @Serializable
    data object MusicChannelScreenRoute : ScreenRoute("Music")
}