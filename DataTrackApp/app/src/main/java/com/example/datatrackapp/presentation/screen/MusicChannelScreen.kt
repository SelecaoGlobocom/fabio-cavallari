package com.example.datatrackapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.datatrackapp.presentation.component.ScreenOnResume
import com.example.datatrackapp.presentation.viewmodel.ChannelScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MusicChannelScreen(title: String) {
    val viewModel: ChannelScreenViewModel = koinViewModel()
    MusicChannelScreen {
        viewModel.trackPageView(title)
    }
}

@Composable
fun MusicChannelScreen(trackPageView: () -> Unit = {}) {
    ScreenOnResume { trackPageView() }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text("Music Channel")
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MusicChanelScreenPrev() {
    MusicChannelScreen()
}