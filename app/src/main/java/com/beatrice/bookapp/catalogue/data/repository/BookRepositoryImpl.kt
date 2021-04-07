package com.beatrice.bookapp.catalogue.data.repository

import com.beatrice.bookapp.catalogue.data.dao.BookDao
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.repository.BookRepository
import com.beatrice.bookapp.core.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun fetchAllBooks(): Flow<Result<List<Book>>> {

        return bookDao.getAllBooks()
            .map { books -> Result.Success(data = books)}
            .catch { exception -> Result.Error(exception.message) }
            .flowOn(Dispatchers.IO)
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