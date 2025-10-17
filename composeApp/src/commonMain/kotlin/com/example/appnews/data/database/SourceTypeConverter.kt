package com.example.appnews.data.database

import androidx.room.TypeConverter
import com.example.appnews.data.model.Source
import kotlinx.serialization.json.Json

class SourceTypeConverter {
    @TypeConverter
    fun fromSourceToString(value: Source): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToSource(value: String): Source {
        return Json.decodeFromString(value)
    }

}