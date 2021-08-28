package com.beatrice.bookapp.catalogue.domain.util

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun listToString(authors: List<String>) = authors.joinToString(separator = ",")

    @TypeConverter
    fun stringToList(value: String) = value.split(",").map { it.trim() }
}
