package com.example.datatrackapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datatrackapp.domain.model.CHANNEL_KEY
import com.example.datatrackapp.domain.model.HOME_BUTTON_EVENT_CLICK_NAME
import com.example.datatrackapp.domain.model.HOME_SCREEN_NAME
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.domain.model.HitType
import com.example.datatrackapp.domain.usecase.SendHitUseCase
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val sendHitUseCase: SendHitUseCase
): ViewModel() {
    fun trackButtonClick(channel: String) {
        viewModelScope.launch {
            val eventHit = Hit(
                type = HitType.EVENT,
                name = HOME_BUTTON_EVENT_CLICK_NAME,
                data = mapOf(CHANNEL_KEY to channel),
            )
            sendHitUseCase.trackHit(eventHit)
        }
    }

    fun trackPageView() {
        viewModelScope.launch {
            val eventHit = Hit(
                type = HitType.PAGE_VIEW,
                name = HOME_SCREEN_NAME,
            )
            sendHitUseCase.trackHit(eventHit)
        }
    }
}