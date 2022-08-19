package com.lalosapps.dictionaryapp.data.remote.dto

import com.lalosapps.dictionaryapp.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
)

fun WordInfoDto.toWordInfoEntity() = WordInfoEntity(
    meanings = meanings.map { it.toMeaning() },
    phonetic = phonetic,
    word = word
)