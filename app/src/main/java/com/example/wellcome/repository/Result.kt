package com.example.wellcome.repository

sealed class Result<out R> {
    data class SuccessNoContent(val responseCode:Int) : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
