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

    @Named(DiConstants.TUTORIAL_FIRST_SCREEN)
    @ActivityScope
    @Binds
    internal abstract fun bindSourcePresenter(
            @Named(DiConstants.TUTORIAL_FIRST_SCREEN) presenter: TutorialPresenterImpl
    ): TutorialPresenter

    @Named(DiConstants.TUTORIAL_SECOND_SCREEN)
    @ActivityScope
    @Binds
    internal abstract fun bindEverythingPresenter(
            @Named(DiConstants.TUTORIAL_SECOND_SCREEN) presenter: TutorialPresenterImpl
    ): TutorialPresenter

    @Named(DiConstants.TUTORIAL_THIRD_SCREEN)
    @ActivityScope
    @Binds
    internal abstract fun bindFavouritesPresenter(
            @Named(DiConstants.TUTORIAL_THIRD_SCREEN) presenter: TutorialPresenterImpl
    ): TutorialPresenter

    @ActivityScope
    @Binds
    internal abstract fun bindTutorialMainNavigator(
            tutorialMainNavigatorImpl: TutorialMainNavigatorImpl
    ): TutorialMainNavigator

    @Module
    companion object {

        @JvmStatic
        @Named(DiConstants.TUTORIAL_FIRST_SCREEN)
        @Provides
        fun provideSourcePresenter(mainNavigatorImpl: TutorialMainNavigatorImpl) =
                TutorialPresenterImpl(mainNavigatorImpl.firstPageTutorial, ImageType.SOURCE)

        @JvmStatic
        @Named(DiConstants.TUTORIAL_SECOND_SCREEN)
        @Provides
        fun provideEverythingPresenter(mainNavigatorImpl: TutorialMainNavigatorImpl) =
                TutorialPresenterImpl(mainNavigatorImpl.secondPageTutorial, ImageType.EVERYTHING)

        @JvmStatic
        @Named(DiConstants.TUTORIAL_THIRD_SCREEN)
        @Provides
        fun provideFavouritesPresenter(mainNavigatorImpl: TutorialMainNavigatorImpl) =
                TutorialPresenterImpl(mainNavigatorImpl.thirdPageTutorial, ImageType.FAVOURITES)
    }
}