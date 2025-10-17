package com.example.appnews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appnews.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(), DB {
    abstract fun newsDao(): NewsDao
}

interface DB {
    fun clearAllTables() {}
}