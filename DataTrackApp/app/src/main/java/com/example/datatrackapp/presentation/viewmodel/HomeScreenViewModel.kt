package com.example.datatrackapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datatrackapp.domain.usecase.SendHitUseCase
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val sendHitUseCase: SendHitUseCase
): ViewModel() {
    fun trackButtonClick(eventName: String) {
        viewModelScope.launch {
            sendHitUseCase.trackHit(eventName)
        }

    }
}