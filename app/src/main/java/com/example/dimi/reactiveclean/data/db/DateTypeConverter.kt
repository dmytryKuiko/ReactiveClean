package com.example.dimi.reactiveclean.data.db

import android.arch.persistence.room.TypeConverter
import java.sql.Date
import java.sql.Timestamp

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

    @TypeConverter
    fun toTimestamp(value: Long?): Timestamp? =
            if(value == null) {
                null
            } else {
                Timestamp(value)
            }

    @TypeConverter
    fun fromTimestamp(timestamp: Timestamp?): Long? = timestamp?.time
}