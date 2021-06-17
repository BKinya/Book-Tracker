package com.beatrice.bookapp.catalogue.data.di

import com.beatrice.bookapp.catalogue.data.repository.CatalogueRepositoryImpl
import com.beatrice.bookapp.catalogue.domain.repository.CatalogueRepository
import com.beatrice.bookapp.core.database.BookAppDatabase
import org.koin.dsl.module

val catalogueDataModules = module {

    factory { (get() as BookAppDatabase).bookDao() }
    factory { CatalogueRepositoryImpl(get()) as CatalogueRepository }
}

