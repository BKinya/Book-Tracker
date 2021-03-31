package com.beatrice.bookapp.catalogue.domain.di

import com.beatrice.bookapp.catalogue.domain.usecase.GetBooksUseCase
import com.beatrice.bookapp.catalogue.domain.usecase.SaveBooksUseCase
import org.koin.dsl.module

val catalogueDomainModules = module {
    factory { GetBooksUseCase(get()) }
    factory { SaveBooksUseCase(get()) }
}