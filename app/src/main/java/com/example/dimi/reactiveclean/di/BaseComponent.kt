package com.example.dimi.reactiveclean.di

import com.example.dimi.reactiveclean.utils.ComponentManager

/**
 * Performs injection in Activities,
 * for saving all components into a HashMap
 * @see ComponentManager
 */
interface BaseComponent<T> {

    fun inject(context: T)
}