package com.example.dimi.reactiveclean.domain.main.section

import com.example.dimi.reactiveclean.models.section.Section
import com.example.dimi.reactiveclean.models.section.SectionDisplayable
import org.hamcrest.Matchers.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.junit.MockitoJUnit

class SectionDomainMapperTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    private val mapper: SectionDomainMapper = SectionDomainMapper()

    @Test
    fun apply_SuccessfullyParsed() {
        val expectedSection = Section("n1", "wT", "wU", "aU")
        val list = listOf(expectedSection)

        val result = mapper.apply(list)

        assertThat(result, hasSize(1))
        assertThat(result[0], instanceOf(SectionDisplayable.Section::class.java))
        assertThat(
            (result[0] as SectionDisplayable.Section).title,
            equalTo(expectedSection.webTitle)
        )
        assertThat((result[0] as SectionDisplayable.Section).id, equalTo(expectedSection.name))
    }
}