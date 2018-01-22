package com.example.dimi.reactiveclean
import org.mockito.Mockito.mock

inline fun <reified T : Any> mock(): T = mock(T::class.java)
