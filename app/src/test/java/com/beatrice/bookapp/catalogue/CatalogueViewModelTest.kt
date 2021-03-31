package com.beatrice.bookapp.catalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.usecase.GetBooksUseCase
import com.beatrice.bookapp.catalogue.domain.usecase.SaveBooksUseCase
import com.beatrice.bookapp.catalogue.ui.CatalogueViewModel
import com.beatrice.bookapp.catalogue.util.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
    val testCoroutineRule = TestCoroutineRule()

    private val getBooksUseCase: GetBooksUseCase = mock(GetBooksUseCase::class.java)
    private val saveBooksUseCase: SaveBooksUseCase = mock(SaveBooksUseCase::class.java)
    private val viewModel = CatalogueViewModel(getBooksUseCase, saveBooksUseCase)

    @Mock
    private lateinit var booksObserver: Observer<List<Book>>

    @Mock
    private lateinit var messageObserver: Observer<String>

    @Test
    fun `test get all books`() = runBlocking {
        testCoroutineRule.runBlockingTest {
            `when`(getBooksUseCase.fetchAllBooks()).thenReturn(testBookResult)

            viewModel.fetchAllBooks()
            viewModel.books.observeForever(booksObserver)

            verify(getBooksUseCase, atLeastOnce()).fetchAllBooks()
            verify(booksObserver).onChanged(testBooks)
            assertThat(viewModel.books.value, `is`(testBooks))
        }
    }

    @Test
    fun `test get all books failed`(){
        testCoroutineRule.runBlockingTest {
            `when`(getBooksUseCase.fetchAllBooks()).thenReturn(testErrorResult)

            viewModel.fetchAllBooks()
            viewModel.error.observeForever(messageObserver)

            verify(getBooksUseCase, atLeastOnce()).fetchAllBooks()
            verify(messageObserver).onChanged(testError)
            assertThat(viewModel.error.value, `is`(testError))
        }
    }

    @Test
    fun `test save books`(){
        testCoroutineRule.runBlockingTest {
            `when`(saveBooksUseCase.saveBooks(anyList())).thenReturn(testMessageResult)

            viewModel.saveBooks(testBooks)
            viewModel.message.observeForever(messageObserver)

            verify(saveBooksUseCase, atLeastOnce()).saveBooks(testBooks)
            verify(messageObserver).onChanged(testMessage)
            assertThat(viewModel.message.value, `is`(testMessage))
        }
    }
}