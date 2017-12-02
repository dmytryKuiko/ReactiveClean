package com.example.dimi.reactiveclean.dagger.modules

import com.example.dimi.reactiveclean.retrofit.EnvelopeConverter
import com.example.dimi.reactiveclean.retrofit.HeaderInterceptor
import com.example.dimi.reactiveclean.retrofit.RedditApi
import com.example.dimi.reactiveclean.retrofit.RxObservableOnCallAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideOkHttpClient(builder: OkHttpClient.Builder, interceptor: Interceptor): OkHttpClient =
            builder.addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()

    @Provides
    @Singleton
    fun provideEnvelopeConverter(): EnvelopeConverter = EnvelopeConverter()


    @Provides
    @Singleton
    fun provideGsonConverterFactory() : GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxObservableOnCallAdapter(): RxObservableOnCallAdapter = RxObservableOnCallAdapter()


    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory() : RxJava2CallAdapterFactory =
            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    @Provides
    @Singleton
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
    fun provideRedditApi(retrofit: Retrofit) = retrofit.create(RedditApi::class.java)
}