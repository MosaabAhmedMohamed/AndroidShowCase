package com.example.androidshowcase.domain


sealed class BaseApiResponse<out T>{
    data class SuccessList<T>(val data: List<T>) :BaseApiResponse<T>()
    data class SuccessItem<T>(val data:T) :BaseApiResponse<T>()
    data class Error(val e: Throwable) : BaseApiResponse<Nothing>()
}
