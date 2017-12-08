package com.example.dimi.reactiveclean.domain.FirstScreen

import com.example.dimi.reactiveclean.models.Article
import com.example.dimi.reactiveclean.models.ArticleDisplayableItem
import io.reactivex.functions.Function
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class FirstScreenDomainMapper
@Inject
constructor() : Function<List<Article>, List<ArticleDisplayableItem>> {
    override fun apply(articleList: List<Article>): List<ArticleDisplayableItem> {
        val newList = mutableListOf<ArticleDisplayableItem>()

        articleList.forEach { article ->
            newList.add(ArticleDisplayableItem(article.source,
                    article.author, article.title, article.url, article.urlToImage,
                    convertDisplayableDateTime(article.publishedAt)))
        }
        return newList
    }


    private fun convertDisplayableDateTime(unconverted: String): String =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.GERMANY).parse(unconverted).toString()
}
