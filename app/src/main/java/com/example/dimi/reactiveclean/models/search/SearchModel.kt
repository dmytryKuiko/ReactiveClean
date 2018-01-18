package com.example.dimi.reactiveclean.models.search

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.dimi.reactiveclean.data.db.TableNames
import java.sql.Timestamp

@Entity(tableName = TableNames.SEARCH)
class SearchModel(
    @ColumnInfo(name = "searched_text")
    val text: String,

    @ColumnInfo(name = "searched_timestamp")
    val dateTime: Timestamp,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)