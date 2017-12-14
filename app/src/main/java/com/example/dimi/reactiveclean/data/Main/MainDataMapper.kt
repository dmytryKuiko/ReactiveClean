package com.example.dimi.reactiveclean.data.Main

import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.ArticleResponse
import io.reactivex.functions.Function
import javax.inject.Inject

//@ActivityScope
class MainDataMapper
@Inject
constructor()
    : Function<ArticleResponse, Article> {

    init {
        val a = 3
        val b  = 2
    }
    override fun apply(response: ArticleResponse): Article {
        if (response.source != null && response.author != null && response.title != null && response.url != null
                && response.urlToImage != null && response.publishedAt != null) {
            return Article(response.source.name, response.author, response.title, response.url,
                    response.urlToImage, response.publishedAt)
        }
        throw TypeNotPresentException("ArticleMapped", Throwable("Wrong response from the server"))
    }
}