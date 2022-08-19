package com.lalosapps.dictionaryapp.core.util

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}
