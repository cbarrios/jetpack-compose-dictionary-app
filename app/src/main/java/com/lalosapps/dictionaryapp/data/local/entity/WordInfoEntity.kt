package com.lalosapps.dictionaryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lalosapps.dictionaryapp.domain.model.Meaning
import com.lalosapps.dictionaryapp.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String,
    @PrimaryKey val id: Int? = null
)

fun WordInfoEntity.toWordInfo() = WordInfo(
    meanings = meanings,
    phonetic = phonetic,
    word = word
)
