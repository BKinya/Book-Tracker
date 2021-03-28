package com.beatrice.bookapp.core.util

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val error: String?) : Result<Nothing>()
}
