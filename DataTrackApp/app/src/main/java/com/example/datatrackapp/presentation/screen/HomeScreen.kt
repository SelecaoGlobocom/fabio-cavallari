package com.example.datatrackapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.datatrackapp.presentation.component.ScreenOnResume
import com.example.datatrackapp.presentation.navigation.ScreenRoute
import com.example.datatrackapp.presentation.theme.DataTrackAppTheme
import com.example.datatrackapp.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeScreenViewModel = koinViewModel()
    HomeScreen(
        trackPageView = {
            viewModel.trackPageView()
        },
        onButtonClick = { screenRoute ->
            viewModel.trackButtonClick(screenRoute.title)
            navController.navigate(screenRoute)
        }
    )
}

@Composable
fun HomeScreen(
    trackPageView: () -> Unit = {},
    onButtonClick: (ScreenRoute) -> Unit = {},
) {
    ScreenOnResume { trackPageView() }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                onButtonClick(ScreenRoute.SportsChanelScreenRoute)
            }
        ) {
            Text("Sports Chanel")
        }
        Button(
            onClick = {
                onButtonClick(ScreenRoute.FilmChanelScreenRoute)
            }
        ) {
            Text("Film Chanel")
        }
        Button(
            onClick = {
                onButtonClick(ScreenRoute.MusicChanelScreenRoute)
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
        HomeScreen() {}
    }
}