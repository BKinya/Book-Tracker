package com.beatrice.bookapp.catalogue.domain.repository

import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.core.util.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun fetchAllBooks(): Flow<Result<List<Book>>>

    suspend fun saveBooks(books: List<Book>): Result<String>

}