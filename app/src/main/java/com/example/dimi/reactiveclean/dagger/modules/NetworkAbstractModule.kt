package com.example.dimi.reactiveclean.dagger.modules

import com.example.dimi.reactiveclean.retrofit.EnvelopeConverter
import com.example.dimi.reactiveclean.retrofit.HeaderInterceptor
import com.example.dimi.reactiveclean.retrofit.RxObservableOnCallAdapter
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import javax.inject.Singleton

@Module
abstract class NetworkAbstractModule {

    @Binds
    @Singleton
    internal abstract fun provideHeaderInterceptor(header: HeaderInterceptor): Interceptor

    @Binds
    @Singleton
    internal abstract fun provideEnvelopeConverterFactory(envelope: EnvelopeConverter): Converter.Factory

    @Binds
    @Singleton
    internal abstract fun provideRxOncallCallAdapterFacory(rxOnCall: RxObservableOnCallAdapter): CallAdapter.Factory

}