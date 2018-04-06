package com.example.dimi.reactiveclean
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.lifecycle.CachingMode
import org.jetbrains.spek.api.lifecycle.LifecycleAware
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.mock

inline fun <reified T : Any> mock(): T = mock(T::class.java)

inline fun <reified T> SpecBody.spekmock(mode: CachingMode = CachingMode.TEST): LifecycleAware<T> {
    return memoized(mode) {
        Mockito.mock(T::class.java)
    }
}
/**
 * Helper functions that are workarounds to kotlin Runtime Exceptions when using kotlin.
 */

/**
 * Returns Mockito.eq() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 *
 * Generic T is nullable because implicitly bounded by Any?.
 */
fun <T> eq(obj: T): T = Mockito.eq<T>(obj)


/**
 * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 */
fun <T> any(): T = Mockito.any<T>()


/**
 * Returns ArgumentCaptor.capture() as nullable type to avoid java.lang.IllegalStateException
 * when null is returned.
 */
fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()


/**
 * Helper function for creating an argumentCaptor in kotlin.
 */
inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> =
    ArgumentCaptor.forClass(T::class.java)
