package com.example.dimi.reactiveclean.data.FirstScreen

import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.data.db.NewsDao
import io.reactivex.Flowable
import javax.inject.Inject

//@ActivityScope
class FirstScreenReactiveStoreImpl
@Inject
constructor(private val newsDao: NewsDao) : FirstScreenReactiveStore<Long, Article> {

    init {
        val a = 3
        val b = 2
    }

    override fun storeSingular(value: Article) {
        newsDao.insert(value)
    }

    override fun storeAll(valueList: List<Article>) {
        newsDao.insert(valueList.toTypedArray())
    }

    override fun getSingular(key: Long): Flowable<Article> = newsDao.getSingleResponseData(key)


    override fun getAll(): Flowable<List<Article>> = newsDao.getAllResponseData()
}