package com.beatrice.bookapp.catalogue.domain

import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="book")
data class Book(
   @PrimaryKey(autoGenerate = true)
   val id: Int,
   val title: CaseMap.Title,
   val author: List<String>,
   val genre: String?, // TODO make this an enum
   val hasRead: Boolean
)
