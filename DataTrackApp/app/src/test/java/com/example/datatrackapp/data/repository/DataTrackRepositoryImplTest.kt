package com.example.datatrackapp.data.repository

import com.example.datatrackapp.data.dao.HitDao
import com.example.datatrackapp.data.dbo.HitDbo
import com.example.datatrackapp.data.dto.HitDto
import com.example.datatrackapp.data.mapper.asDboModel
import com.example.datatrackapp.data.mapper.asDomainModel
import com.example.datatrackapp.data.remoteprovider.TrackApiRemoteProvider
import com.example.datatrackapp.data.utils.Result
import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.domain.model.HitType
import com.example.datatrackapp.plataform.utils.Logger
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.slot
import io.mockk.unmockkObject
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DataTrackRepositoryImplTest {

    private lateinit var remoteProvider: TrackApiRemoteProvider
    private lateinit var dao: HitDao
    private lateinit var repository: DataTrackRepositoryImpl

    private val fakeHit1 = Hit(
        id = 1,
        type = HitType.EVENT,
        name = "test_event_1",
        data = mapOf("key" to "value1"),
        timestamp = 1000L,
        sent = false
    )
    private val fakeHit2 = Hit(
        id = 2,
        type = HitType.PAGE_VIEW,
        name = "test_page_2",
        data = mapOf("key" to "value2"),
        timestamp = 2000L,
        sent = false
    )
    private val fakeHitDbo1 = HitDbo(
        id = 1,
        type = HitType.EVENT,
        name = "test_event_1",
        data = mapOf("key" to "value1"),
        timestamp = 1000L,
        sent = false
    )
    private val testHitDbo2 = HitDbo(
        id = 2,
        type = HitType.PAGE_VIEW,
        name = "test_page_2",
        data = mapOf("key" to "value2"),
        timestamp = 2000L,
        sent = false
    )
    private val fakeHitDto1 = HitDto(
        type = HitType.EVENT,
        name = "test_event_1",
        data = mapOf("key" to "value1"),
        timestamp = 1000L
    )
    private val fakeHitDto2 = HitDto(
        type = HitType.PAGE_VIEW,
        name = "test_page_2",
        data = mapOf("key" to "value2"),
        timestamp = 2000L
    )

    @Before
    fun setUp() {
        remoteProvider = mockk()
        dao =
            mockk(relaxUnitFun = true)
        repository = DataTrackRepositoryImpl(remoteProvider, dao)
        mockkObject(Logger)
        every { Logger.log(any()) } just Runs
    }

    @After
    fun tearDown() {
        unmockkObject(Logger)
        clearAllMocks()
    }

    @Test
    fun `saveHit should call dao_insertHit one time`() = runTest {
        val hitToSave = fakeHit1
        val expectedDbo = fakeHit1.asDboModel()

        repository.saveHit(hitToSave)

        coVerify(exactly = 1) { dao.insertHit(expectedDbo) }
    }

    @Test
    fun `getUnsentHits should call dao_getUnsentHits one time and map to domain model`() = runTest {
        val unsentDboList = listOf(
            fakeHitDbo1,
            testHitDbo2.copy(sent = false)
        )
        coEvery { dao.getUnsentHits() } returns unsentDboList

        val result = repository.getUnsentHits()

        coVerify(exactly = 1) { dao.getUnsentHits() }
        assertEquals(unsentDboList.size, result.size)
        assertEquals(
            unsentDboList.asDomainModel(),
            result
        )
    }

    @Test
    fun `getUnsentHits should return empty list when dao_getUnsentHits returns empty list`() =
        runTest {
            coEvery { dao.getUnsentHits() } returns emptyList()

            val result = repository.getUnsentHits()

            coVerify(exactly = 1) { dao.getUnsentHits() }
            assertTrue(result.isEmpty())
        }

    @Test
    fun `sendPendingHits should call remoteProvider_trackHit one time and dao_markHitsAsSent hit list size times when remoteProvider_trackHit is successful`() =
        runTest {
            val hitsToSend = listOf(fakeHit1, fakeHit2)
            val expectedDtoList = listOf(fakeHitDto1, fakeHitDto2)
            val slot = slot<List<HitDto>>()
            coEvery { remoteProvider.trackHit(capture(slot)) } returns Result.Success(Unit)

            repository.sendPendingHits(hitsToSend)

            coVerify(exactly = 1) { remoteProvider.trackHit(any()) }
            assertEquals(
                expectedDtoList.size,
                slot.captured.size
            )
            coVerify(exactly = slot.captured.size) { dao.markHitAsSent(any()) }
            coVerify(exactly = 1) { dao.markHitAsSent(fakeHit1.id!!) }
            coVerify(exactly = 1) { dao.markHitAsSent(fakeHit2.id!!) }
        }

    @Test
    fun `sendPendingHits should call remoteProvider_trackHit one time and not call dao_markHitsAsSent when remoteProvider_trackHit is not successful`() =
        runTest {
            val hitsToSend = listOf(fakeHit1, fakeHit2)
            coEvery { remoteProvider.trackHit(any()) } returns Result.Error(
                "fake error message",
                "fake error code"
            )

            repository.sendPendingHits(hitsToSend)

            coVerify(exactly = 1) { remoteProvider.trackHit(any()) }
            coVerify(exactly = 0) { dao.markHitAsSent(any()) }
        }
}