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
import com.example.dimi.reactiveclean.extensions.paginator.Paginator
import com.example.dimi.reactiveclean.extensions.paginator.PaginatorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class SectionChosenModule {

    @Binds
    @CustomScope
    abstract internal fun bindPresenter(presenter: SectionChosenPresenterImpl): SectionChosenPresenter

    @Binds
    @CustomScope
    abstract internal fun bindInterractor(interractor: SectionChosenInterractorImpl): SectionChosenInterractor

    @Binds
    @CustomScope
    abstract internal fun bindRepository(repo: SectionChosenRepositoryImpl): SectionChosenRepository

    @Binds
    @CustomScope
    abstract internal fun bindStore(store: SectionChosenStoreImpl): SectionChosenStore

    @Binds
    @CustomScope
    @Named(DiConstants.SECTION_CHOSEN_PAGINATOR)
    abstract internal fun bindPaginator(
            pag: PaginatorImpl<ContentDisplayable>): Paginator<ContentDisplayable>

    @Module
    companion object {

//        @Provides
//        @JvmStatic
//        @Named(DiConstants.SECTION_CHOSEN_PAGINATOR)
//        fun providePaginator(): PaginatorDBImpl<ContentDisplayable> = PaginatorDBImpl()
    }
}