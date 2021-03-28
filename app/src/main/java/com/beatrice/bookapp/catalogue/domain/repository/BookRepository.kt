package com.beatrice.bookapp.catalogue.domain.repository

import com.beatrice.bookapp.catalogue.domain.Book
import com.beatrice.bookapp.core.util.Result

interface BookRepository {
    suspend fun getAllBooks(): Result<List<Book>>

    suspend fun insertBooks(books: List<Book>): Result<Book>

}