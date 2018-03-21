package com.example.dimi.reactiveclean.data.main.content

import com.example.dimi.reactiveclean.data.network.ServiceNewsApi
import com.example.dimi.reactiveclean.models.content.Content
import com.example.dimi.reactiveclean.models.content.ContentPages
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ContentRepositoryImpl
@Inject constructor(
    private val store: ContentStore,
    private val serviceNewsApi: ServiceNewsApi,
    private val mapperDB: ContentDataMapperForDB
) : ContentRepository {

    /**
     * Retrieves all Contents from Database
     * @return - data from Database
     */
    override fun getAllContent(): Flowable<List<Content>> =
        store.getAll()

    /**
     * Deletes all Contents in Database and saves new info
     * @return completable which indicates whether it was successful or not
     */
    override fun deleteAndFetchContent(): Completable =
        serviceNewsApi.getAllContent()
            .map(mapperDB)
            .doOnSuccess(store::deleteAllAndStoreAll)
            .toCompletable()

    /**
     * Loads info for a new page, required for Pagination
     * @param page index of a new page
     * @return completable which indicates whether it was successful or not
     */
    override fun loadNextContentPage(page: Int): Completable {
        return serviceNewsApi.getNextContent(page)
            .map(mapperDB)
            .doOnSuccess(store::storeAll)
            .toCompletable()
    }
}
