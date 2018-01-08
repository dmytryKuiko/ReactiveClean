package com.example.dimi.reactiveclean.di.modules

import com.example.dimi.reactiveclean.data.NewsMain.section_chosen.SectionChosenStore
import com.example.dimi.reactiveclean.data.NewsMain.section_chosen.SectionChosenStoreImpl
import com.example.dimi.reactiveclean.di.DiConstants
import com.example.dimi.reactiveclean.di.scopes.CustomScope
import com.example.dimi.reactiveclean.domain.NewsMain.section_chosen.SectionChosenInterractor
import com.example.dimi.reactiveclean.domain.NewsMain.section_chosen.SectionChosenInterractorImpl
import com.example.dimi.reactiveclean.models.content.ContentDisplayable
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section_chosen.SectionChosenPresenter
import com.example.dimi.reactiveclean.presentation.NewsMain.presenter.section_chosen.SectionChosenPresenterImpl
import com.example.dimi.reactiveclean.repositories.NewsMain.section_chosen.SectionChosenRepository
import com.example.dimi.reactiveclean.repositories.NewsMain.section_chosen.SectionChosenRepositoryImpl
import com.example.dimi.reactiveclean.utils.paginator.PaginatorChanged
import com.example.dimi.reactiveclean.utils.paginator.PaginatorChangedImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
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
            pag: PaginatorChangedImpl<ContentDisplayable>): PaginatorChanged<ContentDisplayable>

    @Module
    companion object {

//        @Provides
//        @JvmStatic
//        @Named(DiConstants.SECTION_CHOSEN_PAGINATOR)
//        fun providePaginator(): PaginatorChangedImpl<ContentDisplayable> = PaginatorChangedImpl()
    }
}