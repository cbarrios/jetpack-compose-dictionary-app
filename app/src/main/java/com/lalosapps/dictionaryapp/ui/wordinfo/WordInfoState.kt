package com.lalosapps.dictionaryapp.ui.wordinfo

import com.lalosapps.dictionaryapp.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)