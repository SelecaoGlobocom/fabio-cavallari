package com.example.datatrackapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.datatrackapp.presentation.navigation.ScreenRoute
import com.example.datatrackapp.presentation.theme.DataTrackAppTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column (
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate(ScreenRoute.SportsChanelScreenRoute)
            }
        ) {
            Text("Sports Chanel")
        }
        Button(
            onClick = {
                navController.navigate(ScreenRoute.FilmChanelScreenRoute)
            }
        ) {
            Text("Film Chanel")
        }
        Button(
            onClick = {
                navController.navigate(ScreenRoute.MusicChanelScreenRoute)
            }
        ) {
            Text("Music Chanel")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPrev() {
    DataTrackAppTheme {
//        HomeScreen()
    }
}