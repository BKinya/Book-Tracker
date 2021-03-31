package com.beatrice.bookapp.catalogue.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beatrice.bookapp.catalogue.domain.model.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    suspend fun getAllBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<Book>)
}