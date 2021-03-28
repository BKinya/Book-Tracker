package com.beatrice.bookapp.catalogue.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.beatrice.bookapp.catalogue.domain.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    suspend fun getAllBooks(): List<Book>

    @Insert
    suspend fun insertBooks(books: List<Book>): String
}