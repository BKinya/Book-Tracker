package com.beatrice.bookapp.catalogue

import com.beatrice.bookapp.catalogue.data.dao.BookDao
import com.beatrice.bookapp.catalogue.data.repository.BookRepositoryImpl
import com.beatrice.bookapp.catalogue.domain.repository.BookRepository
import com.beatrice.bookapp.catalogue.util.testBooks
import com.beatrice.bookapp.core.util.Result
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.Exception

class BookRepositoryTest {
    private var bookDao: BookDao = mock(BookDao::class.java)
    private var repository: BookRepository = BookRepositoryImpl(bookDao)

    @Test
    fun `test get all books`() = runBlocking {
        `when`(bookDao.getAllBooks()).thenReturn(testBooks)

        val res = repository.fetchAllBooks()

        assertThat(res, `is`(Result.Success(testBooks)))
    }

    @Test
    fun `test get all books failed`() = runBlocking{
        `when`(bookDao.getAllBooks()).thenAnswer { throw Exception() }

        val res = repository.fetchAllBooks()

        assertThat(res, `is`(Result.Error(Exception().message)))
    }

    @Test
    fun `test insert books`() = runBlocking {
        val res = repository.saveBooks(testBooks)

        assertThat(res, `is`(Result.Success("Successfully saved data")))
    }

    @Test
    fun `test insert books failed`() = runBlocking {
        `when`(bookDao.insertBooks(testBooks)).thenAnswer { throw Exception() }
        val res = repository.saveBooks(testBooks)

        assertThat(res, `is`(Result.Error(Exception().message)))
    }
}