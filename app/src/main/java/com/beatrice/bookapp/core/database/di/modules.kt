package com.beatrice.bookapp.core.database.di

import com.beatrice.bookapp.BookApp
import com.beatrice.bookapp.core.database.BookAppDatabase
import org.koin.dsl.module

val databaseModules = module {
    single { BookApp.INSTANCE }
    single { BookAppDatabase.getInstance(get()) }
}
