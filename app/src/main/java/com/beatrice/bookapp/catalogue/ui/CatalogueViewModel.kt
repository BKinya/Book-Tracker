package com.beatrice.bookapp.catalogue.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.usecase.GetBooksUseCase
import com.beatrice.bookapp.catalogue.domain.usecase.SaveBooksUseCase
import com.beatrice.bookapp.core.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CatalogueViewModel(
    private val getBooksUseCase: GetBooksUseCase,
    private val saveBooksUseCase: SaveBooksUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private var _books = MutableStateFlow<Result<List<Book>>>(Result.Success(emptyList()))
    val books get() = _books

    private var _message = MutableLiveData<String>()
    val message get() = _message

    private var _error = MutableLiveData<String>()
    val error get() = _error

    init {
        fetchAllBooks()
    }

    fun fetchAllBooks() {
        viewModelScope.launch(dispatcher) {
            getBooksUseCase.fetchAllBooks().collect { result ->
                _books.value = result
            }
        }
    }

    fun saveBooks(books: List<Book>) {
        viewModelScope.launch(dispatcher) {
            when (val result = saveBooksUseCase.saveBooks(books)) {
                is Result.Success -> {
                    _message.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.error)
                }
            }
        }
    }
}
