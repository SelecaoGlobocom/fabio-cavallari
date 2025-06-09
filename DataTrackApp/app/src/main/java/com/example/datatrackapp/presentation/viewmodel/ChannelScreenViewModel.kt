package com.example.datatrackapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datatrackapp.domain.model.CHANNEL_SCREEN_NAME_SUFFIX
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.domain.model.HitType
import com.example.datatrackapp.domain.usecase.TrackHitsUseCase
import kotlinx.coroutines.launch

class ChannelScreenViewModel(
    private val trackHitsUseCase: TrackHitsUseCase
): ViewModel() {
    fun trackPageView(channelTitle: String) {
        viewModelScope.launch {
            val eventHit = Hit(
                type = HitType.PAGE_VIEW,
                name = channelTitle + CHANNEL_SCREEN_NAME_SUFFIX,
            )
            trackHitsUseCase(eventHit)
        }
    }
}