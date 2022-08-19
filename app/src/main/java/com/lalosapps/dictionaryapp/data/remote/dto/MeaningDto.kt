package com.lalosapps.dictionaryapp.data.remote.dto

import com.lalosapps.dictionaryapp.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String
)

fun MeaningDto.toMeaning() = Meaning(
    definitions = definitions.map { it.toDefinition() },
    partOfSpeech = partOfSpeech
)