package com.beatrice.bookapp.catalogue.domain.usecase

import com.beatrice.bookapp.catalogue.domain.repository.CatalogueRepository

class GetBooksUseCase(private val repository: CatalogueRepository) {

    fun fetchAllBooks() = repository.fetchAllBooks()
}