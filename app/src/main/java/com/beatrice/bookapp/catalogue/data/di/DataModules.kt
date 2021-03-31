package com.beatrice.bookapp.catalogue.data.di

import com.beatrice.bookapp.catalogue.data.repository.BookRepositoryImpl
import com.beatrice.bookapp.catalogue.domain.repository.BookRepository
import com.beatrice.bookapp.core.database.BookAppDatabase
import org.koin.dsl.module

val catalogueDataModules = module {
    factory { (get() as BookAppDatabase).bookDao() }
    factory { BookRepositoryImpl(get()) as BookRepository }
}

