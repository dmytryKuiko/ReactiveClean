package com.example.dimi.reactiveclean.retrofit

import com.example.dimi.reactiveclean.models.Envelope
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EnvelopeConverter: Converter.Factory() {
    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, Any>? {
        val envelopedType = TypeToken.getParameterized(Envelope::class.java, type).type
        return if (retrofit != null && envelopedType != null && annotations != null) {
            val delegate: Converter<ResponseBody, Envelope<Any>> = retrofit
                    .nextResponseBodyConverter(this, envelopedType, annotations)
            Converter<ResponseBody, Any> { value: ResponseBody? -> value?.let { delegate.convert(it).data } }
        } else null
    }
}