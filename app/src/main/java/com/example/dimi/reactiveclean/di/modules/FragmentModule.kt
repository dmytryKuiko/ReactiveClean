//package com.example.dimi.reactiveclean.di.modules
//
//import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenDataMapper
//import com.example.dimi.reactiveclean.repositories.FirstScreen.FirstScreenRepositoryImpl
//import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
//import com.example.dimi.reactiveclean.data.FirstScreen.FirstScreenReactiveStoreImpl
//import com.example.dimi.reactiveclean.data.db.NewsDao
//import dagger.Module
//import dagger.Provides
//
//@Module
//class FragmentModule {
//
//    @Provides
//    fun provideArticleMapper(): FirstScreenDataMapper = FirstScreenDataMapper()
//
//    @Provides
//    fun provideReactiveStore(newsDao: NewsDao): FirstScreenReactiveStoreImpl = FirstScreenReactiveStoreImpl(newsDao)
//
//    @Provides
//    fun provideArticleRepository(storeImpl: FirstScreenReactiveStoreImpl,
//                                 serviceNewsApi: ServiceNewsApi,
//                                 firstScreenDataMapper: FirstScreenDataMapper) =
//            FirstScreenRepositoryImpl(storeImpl, serviceNewsApi, firstScreenDataMapper)
//}