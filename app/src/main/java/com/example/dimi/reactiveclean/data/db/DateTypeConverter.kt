package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.TypeConverter
import java.sql.Date

class DateTypeConverter {

    @TypeConverter
    fun toDate(value: Long?): Date? =
        if(value == null) {
            null
        }
        else {
            Date(value)
        }

    @TypeConverter
    fun fromDate(value: Date?): Long? = value?.time
}