package com.example.dimi.reactiveclean.domain.main.content

import com.example.dimi.reactiveclean.extensions.toDisplayable
import com.example.dimi.reactiveclean.models.content.Content
import org.hamcrest.Matchers.hasSize
import org.joda.time.DateTime
import org.junit.Test

import org.junit.Assert.*

class ContentDomainMapperTest {

    @Test
    fun apply_Expected() {
        val content = Content("someName", "Article", "section",
            1516615203578, "http", "pillar")
        val expected = listOf(content)
        val contentDomainMapper = ContentDomainMapper()
        val result = contentDomainMapper.apply(expected)

        assertThat(result, hasSize(1))
        assertEquals(expected[0].name, result[0].title)
        assertEquals(expected[0].webUrl, result[0].url)
        assertEquals(DateTime(expected[0].publicationMills).toDisplayable(), result[0].time)
        assertEquals(expected[0].sectionName, result[0].sectionName)
    }

}