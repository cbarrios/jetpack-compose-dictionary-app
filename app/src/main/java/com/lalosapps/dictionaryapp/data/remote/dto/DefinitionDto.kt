package com.lalosapps.dictionaryapp.data.remote.dto

import com.lalosapps.dictionaryapp.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
)

fun DefinitionDto.toDefinition() = Definition(
    antonyms = antonyms,
    definition = definition,
    example = example,
    synonyms = synonyms
)