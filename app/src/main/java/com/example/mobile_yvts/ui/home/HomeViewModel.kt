package com.example.mobile_yvts.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_yvts.data.SettingsPreferences
import com.example.mobile_yvts.data.Word
import com.example.mobile_yvts.data.WordsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: WordsRepository,
    private val preferences: SettingsPreferences
) : ViewModel() {

    private val _words = repository.getAllWordsStream()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val words: StateFlow<List<Word>> = _words

    private val _currentIndex = MutableStateFlow(0)

    val currentWord: StateFlow<Word?> = combine(_words, _currentIndex) { list, index ->
        list.getOrNull(index)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val displayMode: StateFlow<DisplayMode?> = preferences.displayModeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun setDisplayMode(mode: DisplayMode) {
        viewModelScope.launch {
            preferences.saveDisplayMode(mode)
        }
    }

    fun previousWord() {
        _currentIndex.value = (_currentIndex.value - 1).coerceAtLeast(0)
    }

    fun nextWord() {
        _currentIndex.value = (_currentIndex.value + 1).coerceAtMost(words.value.lastIndex)
    }

    fun saveWord(english: String, mongolian: String) {
        viewModelScope.launch {
            repository.insertWord(Word(english = english, mongolian = mongolian))
        }
    }

    fun updateWord(id: Int, english: String, mongolian: String) {
        viewModelScope.launch {
            repository.updateWord(Word(id = id, english = english, mongolian = mongolian))
        }
    }

    fun deleteCurrentWord() {
        currentWord.value?.let {
            viewModelScope.launch {
                repository.deleteWord(it)
            }
        }
    }
}
