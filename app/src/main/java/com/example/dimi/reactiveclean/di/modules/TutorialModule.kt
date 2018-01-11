package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.navigation.tutorial.TutorialMainNavigator
import com.example.dimi.reactiveclean.navigation.tutorial.TutorialMainNavigatorImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.scopes.ActivityScope
import com.example.dimi.reactiveclean.presentation.tutorial.presenter.TutorialPresenter
import com.example.dimi.reactiveclean.presentation.tutorial.presenter.TutorialPresenterImpl
import com.example.dimi.reactiveclean.models.tutorial.ImageType
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class TutorialModule {

    @Binds
    @ActivityScope
    @Named(DiConstants.TUTORIAL_FIRST_SCREEN)
    internal abstract fun bindSourcePresenter(
            @Named(DiConstants.TUTORIAL_FIRST_SCREEN) presenter: TutorialPresenterImpl): TutorialPresenter

    @Binds
    @ActivityScope
    @Named(DiConstants.TUTORIAL_SECOND_SCREEN)
    internal abstract fun bindEverythingPresenter(
            @Named(DiConstants.TUTORIAL_SECOND_SCREEN) presenter: TutorialPresenterImpl): TutorialPresenter

    @Binds
    @ActivityScope
    @Named(DiConstants.TUTORIAL_THIRD_SCREEN)
    internal abstract fun bindFavouritesPresenter(
            @Named(DiConstants.TUTORIAL_THIRD_SCREEN) presenter: TutorialPresenterImpl): TutorialPresenter

    @Binds
    @ActivityScope
    internal abstract fun bindTutorialMainNavigator(tutorialMainNavigatorImpl: TutorialMainNavigatorImpl): TutorialMainNavigator

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Named(DiConstants.TUTORIAL_FIRST_SCREEN)
        fun provideSourcePresenter(mainNavigatorImpl: TutorialMainNavigatorImpl) =
                TutorialPresenterImpl(mainNavigatorImpl.firstPageTutorial, ImageType.SOURCE)

        @JvmStatic
        @Provides
        @Named(DiConstants.TUTORIAL_SECOND_SCREEN)
        fun provideEverythingPresenter(mainNavigatorImpl: TutorialMainNavigatorImpl) =
                TutorialPresenterImpl(mainNavigatorImpl.secondPageTutorial, ImageType.EVERYTHING)

        @JvmStatic
        @Provides
        @Named(DiConstants.TUTORIAL_THIRD_SCREEN)
        fun provideFavouritesPresenter(mainNavigatorImpl: TutorialMainNavigatorImpl) =
                TutorialPresenterImpl(mainNavigatorImpl.thirdPageTutorial, ImageType.FAVOURITES)
    }
}