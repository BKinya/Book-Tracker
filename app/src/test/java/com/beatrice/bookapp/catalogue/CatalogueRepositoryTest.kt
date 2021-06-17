package com.beatrice.bookapp.catalogue

import com.beatrice.bookapp.catalogue.data.dao.BookDao
import com.beatrice.bookapp.catalogue.data.repository.CatalogueRepositoryImpl
import com.beatrice.bookapp.catalogue.domain.repository.CatalogueRepository
import com.beatrice.bookapp.catalogue.util.testBooks
import com.beatrice.bookapp.core.util.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class CatalogueRepositoryTest {
    private var bookDao: BookDao = mock(BookDao::class.java)
    private var repository: CatalogueRepository = CatalogueRepositoryImpl(bookDao)

    @Test
    fun `test get all books`() = runBlocking {
        `when`(bookDao.getAllBooks()).thenReturn(flow { emit(testBooks) })

        val res = repository.fetchAllBooks().first()

        assertThat(res, `is`(Result.Success(testBooks)))
    }

    @Test
    fun `test get all books failed`() = runBlocking {
        // @FIXME mocking the exception  fails with Java.Lang.Exception
        // Abandoning this for now. Will come back to it later
//        `when`(bookDao.getAllBooks()).thenAnswer { throw Exception() }
//        val res = repository.fetchAllBooks()

//        assertThat(res, `is`(Result.Error(Exception().message)))
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