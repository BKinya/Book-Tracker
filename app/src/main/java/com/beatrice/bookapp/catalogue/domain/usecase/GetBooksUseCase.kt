package com.beatrice.bookapp.catalogue.domain.usecase

import com.beatrice.bookapp.catalogue.domain.repository.BookRepository

class GetBooksUseCase(private val repository: BookRepository) {

    suspend fun fetchAllBooks() = repository.fetchAllBooks()
}