package com.example.dimi.reactiveclean.data.Main

import io.reactivex.Flowable

interface MainReactiveStore<Key, Value> {

    fun storeSingular(value: Value)

    fun storeAll(valueList: List<Value>)

    fun getSingular(key: Key): Flowable<Value>

    fun getAll(): Flowable<List<Value>>

    fun deleteAllAndStoreAll(valueList: List<Value>)
}