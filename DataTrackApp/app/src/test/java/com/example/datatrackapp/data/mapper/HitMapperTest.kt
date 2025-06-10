package com.example.datatrackapp.data.mapper

import com.example.datatrackapp.domain.model.Hit
import com.example.datatrackapp.domain.model.HitType
import junit.framework.TestCase.assertEquals
import org.junit.Test

class HitMapperTest {

    private val fakeHit = Hit(
        id = 99,
        type = HitType.PAGE_VIEW,
        name = "test_page_view",
        data = emptyMap(),
        timestamp = System.currentTimeMillis(),
        sent = false
    )

    @Test
    fun `Hit_as_DboModel should return Hit with id 0`() = run {
        val hitDbo = fakeHit.asDboModel()
        assertEquals(0, hitDbo.id)
        assertEquals(fakeHit.type, hitDbo.type)
        assertEquals(fakeHit.name, hitDbo.name)
        assertEquals(fakeHit.data, hitDbo.data)
        assertEquals(fakeHit.timestamp, hitDbo.timestamp)
        assertEquals(fakeHit.sent, hitDbo.sent)
    }

    @Test
    fun `Hit_as_DtoModel should return Hit with same values`() = run {
        val hitDto = fakeHit.asDtoModel()
        assertEquals(fakeHit.type, hitDto.type)
        assertEquals(fakeHit.name, hitDto.name)
        assertEquals(fakeHit.data, hitDto.data)
        assertEquals(fakeHit.timestamp, hitDto.timestamp)
    }

}