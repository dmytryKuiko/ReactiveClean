package com.example.dimi.reactiveclean.models.content

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.dimi.reactiveclean.data.db.TableNames
import java.sql.Date

/**
 * A class for saving Content into a DB
 */
@Entity(tableName = TableNames.CONTENT)
class Content(val name: String,
              val type: String,

              @ColumnInfo(name = "section_name")
              val sectionName: String,

              @ColumnInfo(name = "publication_time")
              val publicationMills: Long,

              @ColumnInfo(name = "web_url")
              val webUrl: String,

              @ColumnInfo(name = "pillar_name")
              val pillarName: String,

              @PrimaryKey(autoGenerate = true)
              val id: Int? = null)