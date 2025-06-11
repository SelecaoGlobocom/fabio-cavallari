package com.example.datatrackapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.datatrackapp.presentation.navigation.DataTrackNavHost
import com.example.datatrackapp.presentation.theme.DataTrackAppTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            DataTrackAppTheme {
                KoinAndroidContext {
                    Scaffold(
                        modifier = Modifier.Companion.fillMaxSize(),
                    ) { innerPadding ->
                        Box(Modifier.Companion.padding(innerPadding)) {
                            DataTrackNavHost(navHostController)
                        }
                    }
                }
            }
        }
    }
}