package com.example.dimi.reactiveclean.models.content

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.dimi.reactiveclean.data.db.TableNames
import java.sql.Date
import java.sql.Timestamp

@Entity(tableName = TableNames.CONTENT)
class Content(val name: String,
              val type: String,

              @ColumnInfo(name = "section_name")
              val sectionName: String,

              val publicationDate: Date,

              @ColumnInfo(name = "web_url")
              val webUrl: String,

              @ColumnInfo(name = "pillar_name")
              val pillarName: String,

              @PrimaryKey(autoGenerate = true)
              val id: Int? = null) {
}