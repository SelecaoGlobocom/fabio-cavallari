package com.example.datatrackapp.domain.usecase

import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

class TrackHitsUseCaseTest {

    private val saveHitUseCase = mockk<SaveHitUseCase>(relaxed = true)
    private val sendBatchHitsUseCase = mockk<SendBatchHitsUseCase>(relaxed = true)

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `trackHitsUseCase should call saveHitUseCase and sendBatchHitsUseCase one time when workManager is disabled`() = runTest{
        val trackHitsUseCase = TrackHitsUseCase(false, saveHitUseCase, sendBatchHitsUseCase)

        trackHitsUseCase(mockk())

        coVerify(exactly = 1) { saveHitUseCase.invoke(any()) }
        coVerify(exactly = 1) { sendBatchHitsUseCase.invoke() }
    }

    @Test
    fun `trackHitsUseCase should call saveHitUseCase and sendBatchHitsUseCase zero times when workManager is disabled`() = runTest{
        val trackHitsUseCase = TrackHitsUseCase(true, saveHitUseCase, sendBatchHitsUseCase)

        trackHitsUseCase(mockk())

        coVerify(exactly = 1) { saveHitUseCase.invoke(any()) }
        coVerify(exactly = 0) { sendBatchHitsUseCase.invoke() }
    }
}