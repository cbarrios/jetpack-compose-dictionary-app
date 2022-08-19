package com.lalosapps.dictionaryapp.data.remote.dto

data class ErrorResponse(
    val message: String,
    val resolution: String,
    val title: String
)