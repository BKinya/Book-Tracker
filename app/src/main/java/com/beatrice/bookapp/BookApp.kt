package com.beatrice.bookapp

import android.app.Application
import com.beatrice.bookapp.core.di.KoinHelper

class BookApp : Application() {
    companion object {
        lateinit var INSTANCE: BookApp
    }

    init {
        INSTANCE = this
    }
    override fun onCreate() {
        super.onCreate()
        KoinHelper.initDI()
    }
}
