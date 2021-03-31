package com.beatrice.bookapp.catalogue.domain.usecase

import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.repository.BookRepository

class SaveBooksUseCase(private val repository: BookRepository) {

   suspend fun saveBooks(books: List<Book>) = repository.saveBooks(books)
}