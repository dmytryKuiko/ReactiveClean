package com.example.dimi.reactiveclean.data.network

import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * Eliminates unused data from response
 */
class EnvelopeConverter
@Inject constructor() : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<out Annotation>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody, Any>? {
        val envelopedType = TypeToken.getParameterized(EnvelopeResponse::class.java, type).type
        return if (retrofit != null && envelopedType != null && annotations != null) {
            val delegate: Converter<ResponseBody, EnvelopeResponse<Any>> = retrofit
                .nextResponseBodyConverter(this, envelopedType, annotations)
            Converter<ResponseBody, Any> { value: ResponseBody? ->
                value?.let {
                    delegate.convert(it).response
                }
            }
        } else null
    }
}