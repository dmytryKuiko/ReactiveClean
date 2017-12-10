package com.example.dimi.reactiveclean.di.modules

import dagger.Module

@Module
class FirstScreenModule {

//    @Provides
//    @ActivityScope
//    fun provideArticleMapper(): FirstScreenDataMapper = FirstScreenDataMapper()

//    @Provides
//    @ActivityScope
//    fun provideStore(newsDao: NewsDao): FirstScreenReactiveStoreImpl = FirstScreenReactiveStoreImpl(newsDao)

//    @Provides
//    @ActivityScope
//    fun provideArticleRepositoryImpl(store: FirstScreenReactiveStoreImpl, newsApi: ServiceNewsApi, articleMapper: FirstScreenDataMapper): FirstScreenRepositoryImpl =
//            FirstScreenRepositoryImpl(store, newsApi, articleMapper)

//    @Provides
//    @ActivityScope
//    fun provideInterractorRetrieveArticleList(repository: FirstScreenRepository) = FirstScreenInterractorImpl(repository)

//    @Provides
//    @ActivityScope
//    fun provideViewModelArticleDisplayable(activity: MainActivity,
//                                           viewModelArticleDisplayableFactory: ViewModelFactory):
//            FirstScreenViewModelImpl = ViewModelProviders
//            .of(activity, viewModelArticleDisplayableFactory)
//            .get(FirstScreenViewModelImpl::class.java)
}