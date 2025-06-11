package com.example.datatrackapp.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.datatrackapp.data.database.DataTrackDatabase
import com.example.datatrackapp.data.dbo.HitDbo
import com.example.datatrackapp.domain.model.HitType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class HitDaoTest {

    private lateinit var db: DataTrackDatabase
    private lateinit var dao: HitDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            DataTrackDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = db.hitDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertHit_should_insert_a_hit_dbo_into_the_database() = runTest {
        val fakeHitDto = HitDbo(
            id = 1,
            name = "fake_hit",
            type = HitType.PAGE_VIEW,
            timestamp = System.currentTimeMillis(),
            sent = false,
            data = mapOf("key" to "value")
        )

        dao.insertHit(fakeHitDto)

        val hits = dao.getAllHits()
        assertEquals(1, hits.size)
        assertEquals(fakeHitDto.name, hits[0].name)
    }

    @Test
    fun getUnsentHits_should_return_only_unsent_hits() = runTest {
        val fakeSentHit = HitDbo(
            id = 1,
            name = "fake_sent_hit",
            type = HitType.PAGE_VIEW,
            timestamp = 1000L,
            sent = true,
            data = emptyMap()
        )
        val fakeUnsentHit = HitDbo(
            id = 2,
            name = "fake_ unsent_hit",
            type = HitType.EVENT,
            timestamp = 2000L,
            sent = false,
            data = emptyMap()
        )

        dao.insertHit(fakeSentHit)
        dao.insertHit(fakeUnsentHit)

        val unsent = dao.getUnsentHits()

        assertEquals(1, unsent.size)
        assertEquals(fakeUnsentHit.name, unsent[0].name)
    }

    @Test
    fun markHitAsSent_should_update_the_sent_status_of_a_hit() = runTest {
        val fakeUnsentHit = HitDbo(
            id = 1,
            name = "fake_unsent_hit",
            type = HitType.PAGE_VIEW,
            timestamp = 3000L,
            sent = false,
            data = emptyMap()
        )

        val fakeSentHit = HitDbo(
            id = 2,
            name = "fake_sent_hit",
            type = HitType.PAGE_VIEW,
            timestamp = 1000L,
            sent = true,
            data = emptyMap()
        )

        dao.insertHit(fakeUnsentHit)
        dao.insertHit(fakeSentHit)
        dao.markHitAsSent(fakeUnsentHit.id)

        val updated = dao.getAllHits().first { it.id == fakeUnsentHit.id }

        assertTrue(updated.sent)
    }

    @Test
    fun getUnsentHits_should_return_empty_list_after_marking_a_hit_as_sent() = runTest {
        val fakeUnsentHit = HitDbo(
            id = 3,
            name = "fake_unsent_hit",
            type = HitType.PAGE_VIEW,
            timestamp = 3000L,
            sent = false,
            data = emptyMap()
        )

        dao.insertHit(fakeUnsentHit)
        dao.markHitAsSent(fakeUnsentHit.id)

        val unsentHits = dao.getUnsentHits()

        assertEquals(0, unsentHits.size)
    }
}