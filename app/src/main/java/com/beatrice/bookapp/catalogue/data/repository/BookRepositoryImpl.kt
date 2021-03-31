package com.beatrice.bookapp.catalogue.data.repository

import com.beatrice.bookapp.catalogue.data.dao.BookDao
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.repository.BookRepository
import com.beatrice.bookapp.core.util.Result

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override suspend fun fetchAllBooks(): Result<List<Book>> {
        return try {
            val books = bookDao.getAllBooks()
            Result.Success(data = books)
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun saveBooks(books: List<Book>): Result<String> {
        return try {
            bookDao.insertBooks(books)
            Result.Success("Successfully saved data")
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }
}