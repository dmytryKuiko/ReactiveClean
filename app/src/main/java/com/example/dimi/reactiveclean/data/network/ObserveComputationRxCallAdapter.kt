package com.example.dimi.reactiveclean.data.network

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ObserveComputationRxCallAdapter : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        if (getRawType(returnType!!) != Observable::class.java || retrofit == null) {
            return null
        }

        val delegate: CallAdapter<Any, Observable<*>> = retrofit
                .nextCallAdapter(this, returnType, annotations!!) as? CallAdapter<Any, Observable<*>> ?: return null

        return object : CallAdapter<Any, Observable<*>> {
            override fun adapt(call: Call<Any>?): Observable<*> {
                val o = delegate.adapt(call)
                return o.observeOn(Schedulers.computation())
            }

            override fun responseType(): Type {
                return delegate.responseType()
            }
        }
    }
}