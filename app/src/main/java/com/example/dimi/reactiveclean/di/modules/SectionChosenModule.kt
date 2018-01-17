package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenStore
import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenStoreImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.domain.main.sectionChosen.SectionChosenInterractor
import com.example.dimi.reactiveclean.domain.main.sectionChosen.SectionChosenInterractorImpl
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen.SectionChosenPresenter
import com.example.dimi.reactiveclean.presentation.main.presenter.sectionChosen.SectionChosenPresenterImpl
import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenRepository
import com.example.dimi.reactiveclean.data.main.sectionChosen.SectionChosenRepositoryImpl
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorNetwork
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorNetworkImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class SectionChosenModule {

    @CustomScope
    @Binds
    abstract internal fun bindPresenter(presenter: SectionChosenPresenterImpl): SectionChosenPresenter

    @CustomScope
    @Binds
    abstract internal fun bindInterractor(interractor: SectionChosenInterractorImpl): SectionChosenInterractor

    @CustomScope
    @Binds
    abstract internal fun bindRepository(repo: SectionChosenRepositoryImpl): SectionChosenRepository

    @CustomScope
    @Binds
    abstract internal fun bindStore(store: SectionChosenStoreImpl): SectionChosenStore

    @Named(DiConstants.SECTION_CHOSEN_PAGINATOR)
    @CustomScope
    @Binds
    abstract internal fun bindPaginator(
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