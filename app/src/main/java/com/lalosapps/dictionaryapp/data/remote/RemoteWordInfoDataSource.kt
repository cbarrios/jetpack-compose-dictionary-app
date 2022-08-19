package com.lalosapps.dictionaryapp.data.remote

import com.lalosapps.dictionaryapp.data.remote.api.DictionaryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteWordInfoDataSource @Inject constructor(private val dictionaryApi: DictionaryApi) {

    suspend fun getWordInfo(word: String) = withContext(Dispatchers.IO) {
        dictionaryApi.getWordInfo(word)
    }
}