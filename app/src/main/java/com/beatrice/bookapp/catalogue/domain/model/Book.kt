package com.beatrice.bookapp.catalogue.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "book", indices = [Index(value = ["title"], unique = true)])
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: List<String>,
    val genre: String?, // TODO make this an enum
    val hasRead: Boolean
)

