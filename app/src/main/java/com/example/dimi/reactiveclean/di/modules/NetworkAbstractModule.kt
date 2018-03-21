package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.network.EnvelopeConverter
import com.example.dimi.reactiveclean.data.network.HeaderInterceptor
import com.example.dimi.reactiveclean.data.network.ObserveComputationRxCallAdapter
import com.example.dimi.reactiveclean.di.components.AppComponent
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import javax.inject.Singleton

/**
 * Module for AppComponent
 * @see AppComponent
 */
@Module
abstract class NetworkAbstractModule {

    @Binds
    internal abstract fun bindHeaderInterceptor(header: HeaderInterceptor): Interceptor
    @Binds
    internal abstract fun bindEnvelopeConverterFactory(envelope: EnvelopeConverter): Converter.Factory

    @Binds
    internal abstract fun bindRxOncallCallAdapterFacory(adapter: ObserveComputationRxCallAdapter): CallAdapter.Factory

}