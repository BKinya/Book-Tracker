package com.beatrice.bookapp.catalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.usecase.GetBooksUseCase
import com.beatrice.bookapp.catalogue.domain.usecase.SaveBooksUseCase
import com.beatrice.bookapp.catalogue.ui.CatalogueViewModel
import com.beatrice.bookapp.catalogue.util.*
import com.beatrice.bookapp.core.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatalogueViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val getBooksUseCase: GetBooksUseCase = mock(GetBooksUseCase::class.java)
    private val saveBooksUseCase: SaveBooksUseCase = mock(SaveBooksUseCase::class.java)

    private val viewModel = CatalogueViewModel(
        getBooksUseCase,
        saveBooksUseCase,
        mainCoroutineRule.dispatcher
    )

    @Test
    fun `test get all books`() = runBlockingTest {
        mainCoroutineRule.runBlockingTest {
            `when`(getBooksUseCase.fetchAllBooks()).thenReturn(flow { testBookResult })

            viewModel.fetchAllBooks()

            verify(getBooksUseCase, atLeastOnce()).fetchAllBooks()
            viewModel.books.observeOnce {books ->
                assertThat(books, `is`(testBooks))
            }
        }
    }

    @Test
    fun `test get all books failed`() {
        mainCoroutineRule.runBlockingTest {
            `when`(getBooksUseCase.fetchAllBooks()).thenReturn(flow { testErrorResult })

            viewModel.fetchAllBooks()

            verify(getBooksUseCase, atLeastOnce()).fetchAllBooks()
            viewModel.error.observeOnce { error ->
                assertThat(error, `is`(testError))
            }
        }
    }

    @Test
    fun `test save books`() {
        runBlocking {
            `when`(saveBooksUseCase.saveBooks(anyList())).thenReturn(testMessageResult)

            viewModel.saveBooks(testBooks)

            verify(saveBooksUseCase, atLeastOnce()).saveBooks(testBooks)
            viewModel.message.observeOnce { message ->
                assertThat(message, `is`(testMessage))
            }
        }
    }
}