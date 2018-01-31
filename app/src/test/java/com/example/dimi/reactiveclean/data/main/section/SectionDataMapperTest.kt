package com.example.dimi.reactiveclean.data.main.section

import com.example.dimi.reactiveclean.models.section.SectionResponse
import com.example.dimi.reactiveclean.models.section.SingleSectionResponse
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.junit.MockitoJUnit

class SectionDataMapperTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    private val mapper: SectionDataMapper = SectionDataMapper()

    @Test
    fun apply_ResultParsed() {
        val singleSectionList = listOf(
            SingleSectionResponse("123", "webT", "webU", "apiU"),
            SingleSectionResponse("12345", "webTit", "webUrl", "apiUrl")
        )
        val sectionResponse = SectionResponse("status", 81, singleSectionList)

        val result = mapper.apply(sectionResponse)

        assertThat(result, hasSize(2))
        for(i in 0 until result.size) {
            assertThat(result[i].name, equalTo(singleSectionList[i].id))
            assertThat(result[i].webTitle, equalTo(singleSectionList[i].webTitle))
            assertThat(result[i].webUrl, equalTo(singleSectionList[i].webUrl))
            assertThat(result[i].apiUrl, equalTo(singleSectionList[i].apiUrl))
        }
    }
}