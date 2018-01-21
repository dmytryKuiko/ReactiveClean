package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.models.content.ContentPages
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.SingleContentResponse
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import java.io.NotSerializableException

class ContentDataMapperTest {

    private val contentDataMapper = ContentDataMapper()

    val singleContentResponse = mock(SingleContentResponse::class.java)

    @Test
    fun apply_ObjectWithAllFields_Mapped() {
        val contentResponse = ContentResponse(
            "200", 1, 3, 5, 7, 9,
            "max", listOf(singleContentResponse)
        )

        val expectedContentPages = ContentPages("200", 1, 3, 5, 7, 9)

        val resultContentPages = contentDataMapper.apply(contentResponse)
        assertEquals(expectedContentPages, resultContentPages)
    }

    @Test(expected = NotSerializableException::class)
    fun apply_StatusNull_ThrowException() {
        val contentResponse = ContentResponse(
            null, 1, 2, 3, 4, 5, null, null
        )
        contentDataMapper.apply(contentResponse)
    }

    @Test(expected = NotSerializableException::class)
    fun apply_PageSizeNull_ThrowException() {
        val contentResponse = ContentResponse(
            null, 1, 2, 3, 4, 5, null, null
        )
        contentDataMapper.apply(contentResponse)
    }
}