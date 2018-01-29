package com.example.dimi.reactiveclean.domain.main.search

import com.example.dimi.reactiveclean.models.search.SearchModel
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Test

import org.junit.Assert.*
import java.sql.Timestamp

class SearchDomainMapperDBTest {

    private val mapper: SearchDomainMapperDB = SearchDomainMapperDB()

    @Test
    fun apply_Ok_ResultExpected() {
        val searchModel1 = SearchModel("teeext", Timestamp(123000))
        val searchModel2 = SearchModel("teeext2", Timestamp(222000))
        val list = listOf(searchModel1, searchModel2)

        val result = mapper.apply(list)

        assertThat(result, hasSize(2))
        assertThat(result[0].text, equalTo(searchModel1.text))
        assertThat(result[0].dateTime, equalTo("01-01-1970 01:02:03"))

        assertThat(result[1].text, equalTo(searchModel2.text))
        assertThat(result[1].dateTime, equalTo("01-01-1970 01:03:42"))
    }
}