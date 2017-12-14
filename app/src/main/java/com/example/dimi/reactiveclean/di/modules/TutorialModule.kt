package com.example.dimi.reactiveclean.di.modules

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
    @Named(DiConstants.TUTORIAL_SOURCE)
    internal abstract fun provideSourceRepository(
            @Named(DiConstants.TUTORIAL_SOURCE) presenter: TutorialPresenterImpl): TutorialPresenter

    @Binds
    @TutorialScope
    @Named(DiConstants.TUTORIAL_EVERYTHING)
    internal abstract fun provideEverythingRepository(
            @Named(DiConstants.TUTORIAL_EVERYTHING) presenter: TutorialPresenterImpl): TutorialPresenter

    @Binds
    @TutorialScope
    @Named(DiConstants.TUTORIAL_FAVOURITES)
    internal abstract fun provideFavouritesRepository(
            @Named(DiConstants.TUTORIAL_FAVOURITES) presenter: TutorialPresenterImpl): TutorialPresenter

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Named(DiConstants.TUTORIAL_SOURCE)
        fun provideSourcePresenter() = TutorialPresenterImpl(ImageType.SOURCE)

        @JvmStatic
        @Provides
        @Named(DiConstants.TUTORIAL_EVERYTHING)
        fun provideEverythingPresenter() = TutorialPresenterImpl(ImageType.EVERYTHING)

        @JvmStatic
        @Provides
        @Named(DiConstants.TUTORIAL_FAVOURITES)
        fun provideFavouritesPresenter() = TutorialPresenterImpl(ImageType.FAVOURITES)
    }
}