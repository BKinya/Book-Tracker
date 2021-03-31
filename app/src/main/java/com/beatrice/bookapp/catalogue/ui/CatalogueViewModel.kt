package com.beatrice.bookapp.catalogue.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.usecase.GetBooksUseCase
import com.beatrice.bookapp.catalogue.domain.usecase.SaveBooksUseCase
import com.beatrice.bookapp.core.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogueViewModel(
    private val getBooksUseCase: GetBooksUseCase,
    private val saveBooksUseCase: SaveBooksUseCase
) : ViewModel() {
    private var _books = MutableLiveData<List<Book>>()
    val books get() = _books

    private var _message = MutableLiveData<String>()
    val message get() = _message

    private var _error = MutableLiveData<String>()
    val error get() = _error

    init {
        fetchAllBooks()
    }

    fun fetchAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getBooksUseCase.fetchAllBooks()) {
                is Result.Success -> {
                    _books.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.error)
                }
            }
        }
    }

    fun saveBooks(books: List<Book>) {
        viewModelScope.launch(Dispatchers.IO) {
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