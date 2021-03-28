package com.beatrice.bookapp.catalogue.data.repository

import com.beatrice.bookapp.catalogue.domain.Book
import com.beatrice.bookapp.catalogue.domain.repository.BookRepository
import com.beatrice.bookapp.core.util.Result

class BookRepositoryImpl: BookRepository {
    override suspend fun getAllBooks(): Result<List<Book>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertBooks(books: List<Book>): Result<Book> {
        TODO("Not yet implemented")
    }
}