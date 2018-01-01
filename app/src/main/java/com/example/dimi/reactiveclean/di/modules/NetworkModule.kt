package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.data.network.EnvelopeConverter
import com.example.dimi.reactiveclean.data.network.HeaderInterceptor
import com.example.dimi.reactiveclean.data.network.ObserveComputationRxCallAdapter
import com.example.dimi.reactiveclean.utils.SchedulersProvider
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder, headerInterceptor: Interceptor,
                            loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            builder.addInterceptor(headerInterceptor).addInterceptor(loggingInterceptor).build()

    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    @Provides
    fun provideEnvelopeConverter(): EnvelopeConverter = EnvelopeConverter()

    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideRxObservableOnCallAdapter(): ObserveComputationRxCallAdapter = ObserveComputationRxCallAdapter()

    @Provides
    fun provideRxJava2CallAdapterFactory(schedulers: SchedulersProvider) : RxJava2CallAdapterFactory =
            RxJava2CallAdapterFactory.createWithScheduler(schedulers.io())

    @Provides
    fun provideRetrofit(retrofitBuilder: Retrofit.Builder,
                        url: String,
                        okHttpClient: OkHttpClient,
                        envelopeConverter: Converter.Factory,
                        gsonConverter: GsonConverterFactory,
                        rxObservableOnCallAdapter: CallAdapter.Factory,
                        rxJava2CallAdapter: RxJava2CallAdapterFactory): Retrofit {
        return retrofitBuilder
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(envelopeConverter)
                .addConverterFactory(gsonConverter)
                .addCallAdapterFactory(rxObservableOnCallAdapter)
                .addCallAdapterFactory(rxJava2CallAdapter).build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit) = retrofit.create(ServiceNewsApi::class.java)
}