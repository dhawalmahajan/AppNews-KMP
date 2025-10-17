package com.example.appnews.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appnews.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Query("SELECT * FROM articleTable")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM articleTable WHERE articleId = :articleId")
    suspend fun getArticle(articleId: String): Article?

    @Delete
    suspend fun delete(article: Article)


    @Query("Delete FROM articleTable")
    suspend fun deleteAllBookmarkedArticles()

}