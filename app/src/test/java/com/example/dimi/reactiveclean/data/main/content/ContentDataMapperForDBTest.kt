package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentResponse
import com.example.dimi.reactiveclean.models.content.SingleContentResponse
import org.hamcrest.Matchers.hasSize
import org.junit.Test

import org.junit.Assert.*

class ContentDataMapperForDBTest {
    @Test
    fun apply_AllFields_Mapped() {
        val webTitleToName = "TITLE"
        val type = "SomeType"
        val sectionName = "SomeSectionNameee"
        val webUrl = "http:"
        val pillarName = "Pillaaaarrr"
        val singleResponse = SingleContentResponse(
            "1", type, "someSection",
            sectionName, "2018-01-21T16:08:04Z", webTitleToName,
            webUrl, "API", pillarName
        )
        val contentResponse = ContentResponse(
            null, null, null, null,
            null, null, null, listOf(singleResponse)
        )
        val expectedContent =
            Content(webTitleToName, type, sectionName, 1516550884000, webUrl, pillarName)

        val mapper = ContentDataMapperForDB()
        val resultContent = mapper.apply(contentResponse)

        assertThat(resultContent, hasSize(1))
        assertEquals(expectedContent.name, resultContent[0].name)
        assertEquals(expectedContent.type, resultContent[0].type)
        assertEquals(expectedContent.sectionName, resultContent[0].sectionName)
        assertEquals(expectedContent.publicationMills, resultContent[0].publicationMills)
        assertEquals(expectedContent.webUrl, resultContent[0].webUrl)
        assertEquals(expectedContent.pillarName, resultContent[0].pillarName)
    }

    @Test
    fun apply_FieldMissing_Empty() {
        val webTitleToName = "TITLE"
        val type = "SomeType"
        val sectionName = null
        val webUrl = "http:"
        val pillarName = "Pillaaaarrr"
        val singleResponse = SingleContentResponse(
            "1", type, "someSection",
            sectionName, "2018-01-21T16:08:04Z", webTitleToName,
            webUrl, "API", pillarName
        )
        val contentResponse = ContentResponse(
            null, null, null, null,
            null, null, null, listOf(singleResponse)
        )

        val mapper = ContentDataMapperForDB()
        val resultContent = mapper.apply(contentResponse)

        assertThat(resultContent, hasSize(0))
    }
}