package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenStore
import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenStoreImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen.SectionChosenPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen.SectionChosenPresenterImpl
import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenRepository
import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenRepositoryImpl
import com.example.dimi.reactiveclean.di.components.SectionChosenComponent
import com.example.dimi.reactiveclean.domain.main.sectionChosen.SectionChosenInteractor
import com.example.dimi.reactiveclean.domain.main.sectionChosen.SectionChosenInteractorImpl
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorNetwork
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorNetworkImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

/**
 * Module for SectionChosenComponent
 * @see SectionChosenComponent
 */
@Module
abstract class SectionChosenModule {

    @CustomScope
    @Binds
    internal abstract fun bindPresenter(presenter: SectionChosenPresenterImpl): SectionChosenPresenter

    @CustomScope
    @Binds
    internal abstract fun bindinteractor(interactor: SectionChosenInteractorImpl): SectionChosenInteractor

    @CustomScope
    @Binds
    internal abstract fun bindRepository(repo: SectionChosenRepositoryImpl): SectionChosenRepository
    @CustomScope
    @Binds
    internal abstract fun bindStore(store: SectionChosenStoreImpl): SectionChosenStore

    @Named(DiConstants.SECTION_CHOSEN_PAGINATOR)
    @CustomScope
    @Binds
    internal abstract fun bindPaginator(
        paginator: PaginatorNetworkImpl<ContentDisplayable>
    ): PaginatorNetwork<ContentDisplayable>

    @Module
    companion object {

//        @Provides
//        @JvmStatic
//        @Named(DiConstants.SECTION_CHOSEN_PAGINATOR)
//        fun providePaginator(): PaginatorDBImpl<ContentDisplayable> = PaginatorDBImpl()
    }
}