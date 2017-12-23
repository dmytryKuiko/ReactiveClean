package com.example.dimi.reactiveclean.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.dimi.reactiveclean.data.db.TableNames

@Entity(tableName = TableNames.SECTIONS)
class Section(val name: String,
              @ColumnInfo(name = "web_title")
              val webTitle: String,
              @ColumnInfo(name = "web_url")
              val webUrl: String,
              @ColumnInfo(name = "api_url")
              val apiUrl: String,
              @PrimaryKey(autoGenerate = true)
              val id: Int? = null) {
}