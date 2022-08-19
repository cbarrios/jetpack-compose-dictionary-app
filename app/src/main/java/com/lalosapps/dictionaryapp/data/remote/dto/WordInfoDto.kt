package com.lalosapps.dictionaryapp.data.remote.dto

import com.lalosapps.dictionaryapp.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
)

fun WordInfoDto.toWordInfo() = WordInfo(
    meanings = meanings.map { it.toMeaning() },
    phonetic = phonetic,
    word = word
)