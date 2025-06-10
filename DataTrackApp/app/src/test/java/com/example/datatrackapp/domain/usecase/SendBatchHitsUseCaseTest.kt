package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.data.repository.DataTrackRepository
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.domain.model.HitType
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

private const val TEST_BATCH_TRASH_HOLD = 5

class SendBatchHitsUseCaseTest {

    private val repository: DataTrackRepository = mockk()
    private val sendBatchHitsUseCase: SendBatchHitsUseCase =
        SendBatchHitsUseCase(TEST_BATCH_TRASH_HOLD, repository)

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private fun createFakeUnsentHits(count: Int): List<Hit> {
        return List(count) { index ->
            Hit(
                id = index,
                type = HitType.PAGE_VIEW,
                name = "hit_$index",
                data = emptyMap(),
                sent = false
            )
        }
    }

    @Test
    fun `sendBatchHitsUseCase should call repository_getUnsentHits one time and repository_sendPendingHits zero times when unsentHits are less than threshold`() = runTest {
        val unsentHits = createFakeUnsentHits(TEST_BATCH_TRASH_HOLD - 1)
        coEvery { repository.getUnsentHits() } returns unsentHits

        sendBatchHitsUseCase()

        coVerify(exactly = 1) { repository.getUnsentHits() }
        coVerify(exactly = 0) { repository.sendPendingHits(any()) }
    }

    @Test
    fun `sendBatchHitsUseCase should call repository_getUnsentHits one time and repository_sendPendingHits zero times when unsentHits are equal to threshold`() = runTest {
        val unsentHits = createFakeUnsentHits(TEST_BATCH_TRASH_HOLD)
        coEvery { repository.getUnsentHits() } returns unsentHits
        coEvery { repository.sendPendingHits(any()) } just Runs

        sendBatchHitsUseCase()

        coVerify(exactly = 1) { repository.getUnsentHits() }
        coVerify(exactly = 1) { repository.sendPendingHits(unsentHits) }
    }

    @Test
    fun `sendBatchHitsUseCase should call repository_getUnsentHits one time and repository_sendPendingHits zero times when unsentHits are greater than threshold`() = runTest {
        val unsentHits = createFakeUnsentHits(TEST_BATCH_TRASH_HOLD + 1)
        coEvery { repository.getUnsentHits() } returns unsentHits
        coEvery { repository.sendPendingHits(any()) } just Runs

        sendBatchHitsUseCase()

        coVerify(exactly = 1) { repository.getUnsentHits() }
        coVerify(exactly = 1) { repository.sendPendingHits(unsentHits) }
    }
}