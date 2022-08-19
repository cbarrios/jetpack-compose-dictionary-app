package com.lalosapps.dictionaryapp.domain.repository

import com.lalosapps.dictionaryapp.core.util.Resource
import com.lalosapps.dictionaryapp.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}