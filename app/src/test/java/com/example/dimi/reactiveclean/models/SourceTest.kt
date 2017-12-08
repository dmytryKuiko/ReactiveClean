package com.example.dimi.reactiveclean.models

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class SourceTest {

//    @Before
//    fun setUp(): Source = Source("id1", "name1")

    @Test
    fun getId() {
        val id = "id1"
        val name = "name1"

        val source = Source(id, name)
        assertEquals(id, source.id)
    }

    @Test
    fun getName() {
        val id = "id1"
        val name = "name1"

        val source = Source(id, name)
        assertEquals(name, source.name)
    }

}