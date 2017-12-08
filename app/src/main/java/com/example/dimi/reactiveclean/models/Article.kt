package com.example.dimi.reactiveclean.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "table_article")
data class Article(val source: String,
              val author: String,
              val title: String,
              val url: String,
              val urlToImage: String,
              val publishedAt: String,
              @PrimaryKey(autoGenerate = true)
              val id: Int? = null)