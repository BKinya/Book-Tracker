package com.beatrice.bookapp.catalogue.ui.di

import com.beatrice.bookapp.catalogue.ui.CatalogueViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val catalogueViewModelModules = module {
    viewModel { CatalogueViewModel(get(), get()) }
}