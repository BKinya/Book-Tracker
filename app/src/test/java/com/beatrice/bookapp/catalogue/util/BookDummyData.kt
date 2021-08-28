package com.beatrice.bookapp.catalogue.util

import com.beatrice.bookapp.catalogue.domain.model.Book
import com.beatrice.bookapp.core.util.Result

val testBooks = listOf(
    Book(
        id = 0,
        title = "... Dance of the Jakaranda",
        author = listOf("Peter kimani"),
        genre = "Historical Fiction",
        hasRead = true
    )
)

val testBookResult = Result.Success(data = testBooks)

const val testMessage = "Success"

val testMessageResult = Result.Success(data = testMessage)

const val testError = "Failed"

var testErrorResult = Result.Error(error = testError)

