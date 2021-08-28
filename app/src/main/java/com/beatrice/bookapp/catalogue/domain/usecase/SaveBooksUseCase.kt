package com.beatrice.bookapp.catalogue.domain.usecase

import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.repository.CatalogueRepository

class SaveBooksUseCase(private val repository: CatalogueRepository) {

   suspend fun saveBooks(books: List<Book>) = repository.saveBooks(books)
}
