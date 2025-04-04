package com.example.mobile_yvts.ui.word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_yvts.data.Word
import com.example.mobile_yvts.data.WordsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WordEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: WordsRepository
) : ViewModel() {

    private val wordId: Int = checkNotNull(savedStateHandle["wordId"])

    val word = repository.getWordStream(wordId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun updateWord(english: String, mongolian: String) {
        viewModelScope.launch {
            repository.updateWord(Word(id = wordId, english = english, mongolian = mongolian))
        }
    }
}
