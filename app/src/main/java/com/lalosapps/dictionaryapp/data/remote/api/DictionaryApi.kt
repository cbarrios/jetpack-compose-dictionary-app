package com.lalosapps.dictionaryapp.data.remote.api

import com.lalosapps.dictionaryapp.data.remote.dto.WordInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWordInfo(@Path("word") word: String): Response<List<WordInfoDto>>
}