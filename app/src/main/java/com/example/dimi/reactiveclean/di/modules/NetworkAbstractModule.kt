package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.network.EnvelopeConverter
import com.example.dimi.reactiveclean.data.network.HeaderInterceptor
import com.example.dimi.reactiveclean.data.network.ObserveComputationRxCallAdapter
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import javax.inject.Singleton

@Module
abstract class NetworkAbstractModule {

    @Binds
    internal abstract fun provideHeaderInterceptor(header: HeaderInterceptor): Interceptor

    @Binds
    internal abstract fun provideEnvelopeConverterFactory(envelope: EnvelopeConverter): Converter.Factory

    @Binds
    internal abstract fun provideRxOncallCallAdapterFacory(adapter: ObserveComputationRxCallAdapter): CallAdapter.Factory

}