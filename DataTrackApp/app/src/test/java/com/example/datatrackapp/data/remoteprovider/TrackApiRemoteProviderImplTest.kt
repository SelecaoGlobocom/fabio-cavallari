package com.example.datatrackapp.data.remoteprovider

import com.example.datatrackapp.data.client.TrackApiClient
import com.example.datatrackapp.data.dto.HitDto
import com.example.datatrackapp.data.utils.Result
import com.example.datatrackapp.domain.model.HitType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

class TrackApiRemoteProviderImplTest {

    private val fakeHitDto = HitDto(
        type = HitType.PAGE_VIEW,
        name = "test_page_view",
        data = emptyMap(),
        timestamp = System.currentTimeMillis()
    )
    val mockClient = mockk<TrackApiClient>()
    val provider = TrackApiRemoteProviderImpl(mockClient)

    @Test
    fun `trackHit should return Result_Success when client_trackHit is successful`() = runTest {
        coEvery { mockClient.trackHit(any()) } returns Response.success(Unit)

        val result = provider.trackHit(listOf(fakeHitDto))

        assertTrue(result is Result.Success)
        coVerify(exactly = 1) { mockClient.trackHit(any()) }
    }

    @Test
    fun `trackHit should return Result_Error when client_trackHit is not successful`() = runTest {
        val fakeErrorCode = 500
        coEvery { mockClient.trackHit(any()) } returns Response.error(fakeErrorCode, mockk(relaxed = true))

        val result = provider.trackHit(listOf(fakeHitDto))

        assertTrue(result is Result.Error)
        assertEquals(fakeErrorCode.toString(), (result as Result.Error).errorCode.toString())
        coVerify(exactly = 1) { mockClient.trackHit(any()) }
    }

}