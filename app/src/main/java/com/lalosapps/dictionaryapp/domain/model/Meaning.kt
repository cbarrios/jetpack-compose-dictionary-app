package com.lalosapps.dictionaryapp.domain.model

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)
