package com.beatrice.bookapp.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.beatrice.bookapp.catalogue.data.dao.BookDao
import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.catalogue.domain.util.Converters

@Database(entities = [Book::class], version = 1)
@TypeConverters(Converters::class)
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
                    "Book-app-database"
                )
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

    abstract fun bookDao(): BookDao
}
