package com.example.dimi.reactiveclean.models

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean


class SingleEventLiveData<T> : MutableLiveData<T>() {

    private val TAG = "SingleLiveEvent"

    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        super.observe(owner, Observer { t: T? ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    override fun postValue(value: T?) {
        mPending.set(true)
        super.postValue(value)
    }

    fun call() = postValue(null)
}