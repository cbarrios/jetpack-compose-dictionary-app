package com.lalosapps.dictionaryapp.domain.usecase

import com.lalosapps.dictionaryapp.core.util.Resource
import com.lalosapps.dictionaryapp.domain.model.WordInfo
import com.lalosapps.dictionaryapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(private val repository: WordInfoRepository) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {}
        }
        return repository.getWordInfo(word)
    }
}