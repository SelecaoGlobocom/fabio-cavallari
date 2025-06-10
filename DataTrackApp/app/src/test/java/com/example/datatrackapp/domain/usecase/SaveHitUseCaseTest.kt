package com.example.datatrackapp.domain.usecase

import com.example.datatrackapp.data.repository.DataTrackRepository
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.domain.model.HitType
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveHitUseCaseTest {

    private val repository: DataTrackRepository = mockk(relaxed = true)
    private val saveHitUseCase = SaveHitUseCase(repository)

    @Test
    fun `saveHit should call repository_saveHit one time`() = runTest{
        val hit = Hit(
            id = 1,
            type = HitType.EVENT,
            timestamp = 1678886400000,
            name = "hit_test",
            data = emptyMap(),
            sent = false
        )

        saveHitUseCase.invoke(hit)

        coVerify(exactly = 1) { repository.saveHit(any()) }
    }
}