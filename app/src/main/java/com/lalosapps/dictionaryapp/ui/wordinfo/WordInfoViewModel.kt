package com.lalosapps.dictionaryapp.ui.wordinfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalosapps.dictionaryapp.core.util.Constants
import com.lalosapps.dictionaryapp.core.util.Resource
import com.lalosapps.dictionaryapp.core.util.UiEvent
import com.lalosapps.dictionaryapp.domain.usecase.GetWordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    var state by mutableStateOf(WordInfoState())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        searchQuery = query
        searchJob?.cancel()
        if (query.isBlank()) {
            state = state.copy(isLoading = false)
            return
        }
        searchJob = viewModelScope.launch {
            delay(Constants.ON_SEARCH_TIME_DELAY)
            getWordInfoUseCase(query).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UiEvent.ShowSnackbar(result.message!!))
                    }
                }
            }.launchIn(this)
        }
    }
}