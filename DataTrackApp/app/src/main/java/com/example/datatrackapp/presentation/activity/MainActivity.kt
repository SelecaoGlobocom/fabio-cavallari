package com.example.datatrackapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.datatrackapp.data.dao.HitDao
import com.example.datatrackapp.plataform.utils.Logger
import com.example.datatrackapp.presentation.navigation.DataTrackNavHost
import com.example.datatrackapp.presentation.theme.DataTrackAppTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            DataTrackAppTheme {
                KoinAndroidContext {
                    Scaffold(
                        modifier = Modifier.Companion.fillMaxSize(),
                        floatingActionButton = {
                            Column {
                                FloatingActionButton(
                                    onClick = {
                                        val hitDao: HitDao = get()
                                        coroutineScope.launch {
                                            val hits = hitDao.getAllHits()
                                            hits.forEach { Logger.log(it) }
                                        }
                                        Logger.log("show hits")
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.List,
                                        contentDescription = "Show Hits"
                                    )
                                }
                                Spacer(Modifier.Companion.height(16.dp))
                            }

                        },
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