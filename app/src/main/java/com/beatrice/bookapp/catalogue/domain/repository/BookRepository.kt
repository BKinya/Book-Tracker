package com.beatrice.bookapp.catalogue.domain.repository

import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.core.util.Result

interface BookRepository {
    suspend fun fetchAllBooks(): Result<List<Book>>

    suspend fun saveBooks(books: List<Book>): Result<String>

}