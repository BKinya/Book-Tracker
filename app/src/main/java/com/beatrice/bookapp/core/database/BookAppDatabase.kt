package com.beatrice.bookapp.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = emptyArray(), version = 1)
abstract class BookAppDatabase: RoomDatabase() {
    companion object{
        @Volatile
        var INSTANCE: BookAppDatabase? = null
        
        fun getInstance(context: Context): BookAppDatabase {
            var instance = INSTANCE
            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookAppDatabase::class.java,
                    "mavuno-database"
                )
                    .build()
                INSTANCE = instance
            }
            return instance
        }
        // Put your DAOs here


    }
}