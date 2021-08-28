package com.beatrice.bookapp.catalogue.ui.di

import com.beatrice.bookapp.catalogue.ui.CatalogueViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val catalogueViewModelModules = module {
    factory { Dispatchers.IO }
    viewModel { CatalogueViewModel(get(), get(), get()) }
}
