package com.example.datatrackapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.datatrackapp.presentation.component.ScreenOnResume
import com.example.datatrackapp.presentation.navigation.ScreenRoute
import com.example.datatrackapp.presentation.theme.DataTrackAppTheme
import com.example.datatrackapp.presentation.utils.FILM_CHANNEL_BUTTON_TAG
import com.example.datatrackapp.presentation.utils.HOME_SCREEN_TAG
import com.example.datatrackapp.presentation.utils.MUSIC_CHANNEL_BUTTON_TAG
import com.example.datatrackapp.presentation.utils.SPORTS_CHANNEL_BUTTON_TAG
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
        Modifier.fillMaxSize()
            .testTag(HOME_SCREEN_TAG),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.testTag(SPORTS_CHANNEL_BUTTON_TAG),
            onClick = {
                onButtonClick(ScreenRoute.SportsChannelScreenRoute)
            }
        ) {
            Text("Sports Channel")
        }
        Button(
            modifier = Modifier.testTag(FILM_CHANNEL_BUTTON_TAG),
            onClick = {
                onButtonClick(ScreenRoute.FilmChannelScreenRoute)
            }
        ) {
            Text("Film Channel")
        }
        Button(
            modifier = Modifier.testTag(MUSIC_CHANNEL_BUTTON_TAG),
            onClick = {
                onButtonClick(ScreenRoute.MusicChannelScreenRoute)
            }
        ) {
            Text("Music Channel")
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