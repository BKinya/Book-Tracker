package com.beatrice.bookapp.core.di
import com.beatrice.bookapp.BookApp
import com.beatrice.bookapp.catalogue.data.di.catalogueDataModules
import com.beatrice.bookapp.catalogue.domain.di.catalogueDomainModules
import com.beatrice.bookapp.catalogue.ui.di.catalogueViewModelModules
import com.beatrice.bookapp.core.database.di.databaseModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

object KoinHelper {
    fun initDI() {
        val modules = listOf(
            databaseModules,
            catalogueDataModules,
            catalogueDomainModules,
            catalogueViewModelModules
        )
        startKoin {
            EmptyLogger()
            androidContext(BookApp.INSTANCE)
            modules(modules)
        }
    }
}
