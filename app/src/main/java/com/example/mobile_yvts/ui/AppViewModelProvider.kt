package com.example.mobile_yvts.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mobile_yvts.WordApp
import com.example.mobile_yvts.ui.home.HomeViewModel
import com.example.mobile_yvts.ui.word.WordEditViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val savedStateHandle = this.createSavedStateHandle()
            WordEditViewModel(
                savedStateHandle,
                appApplication().container.wordsRepository
            )
        }

        initializer {
            HomeViewModel(
                repository = appApplication().container.wordsRepository,
                preferences = appApplication().settingsPreferences
            )
        }
    }
}

fun CreationExtras.appApplication(): WordApp =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as WordApp)
