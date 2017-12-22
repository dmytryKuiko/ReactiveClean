package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.navigation.Tutorial.TutorialMainNavigator
import com.example.dimi.reactiveclean.navigation.Tutorial.TutorialMainNavigatorImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.scopes.TutorialScope
import com.example.dimi.reactiveclean.presentation.Tutorial.presenter.TutorialPresenter
import com.example.dimi.reactiveclean.presentation.Tutorial.presenter.TutorialPresenterImpl
import com.example.dimi.reactiveclean.presentation.Tutorial.view.ImageType
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class TutorialModule {

    @Binds
    @TutorialScope
    @Named(DiConstants.TUTORIAL_FIRST_SCREEN)
    internal abstract fun bindSourcePresenter(
            @Named(DiConstants.TUTORIAL_FIRST_SCREEN) presenter: TutorialPresenterImpl): TutorialPresenter

    @Binds
    @TutorialScope
    @Named(DiConstants.TUTORIAL_SECOND_SCREEN)
    internal abstract fun bindEverythingPresenter(
            @Named(DiConstants.TUTORIAL_SECOND_SCREEN) presenter: TutorialPresenterImpl): TutorialPresenter

    @Binds
    @TutorialScope
    @Named(DiConstants.TUTORIAL_THIRD_SCREEN)
    internal abstract fun bindFavouritesPresenter(
            @Named(DiConstants.TUTORIAL_THIRD_SCREEN) presenter: TutorialPresenterImpl): TutorialPresenter

    @Binds
    @TutorialScope
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