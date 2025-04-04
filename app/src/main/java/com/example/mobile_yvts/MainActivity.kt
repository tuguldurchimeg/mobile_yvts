package com.example.mobile_yvts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.mobile_yvts.data.SettingsPreferences
import com.example.mobile_yvts.ui.home.HomeViewModel
import com.example.mobile_yvts.ui.WordNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = SettingsPreferences(applicationContext)
        val repository = (application as WordApp).container.wordsRepository

        setContent {
            val navController = rememberNavController()
            val viewModel = HomeViewModel(repository, preferences)

            val words by viewModel.words.collectAsState()
            val currentWord by viewModel.currentWord.collectAsState()
            val displayMode by viewModel.displayMode.collectAsState()

            MaterialTheme {
                Surface {
                    if (displayMode != null) {
                        WordNavGraph(
                            navController = navController,
                            words = words,
                            currentWord = currentWord,
                            displayMode = displayMode!!,
                            onPrevious = viewModel::previousWord,
                            onNext = viewModel::nextWord,
                            onDelete = viewModel::deleteCurrentWord,
                            onSaveWord = viewModel::saveWord,
                            onUpdateWord = viewModel::updateWord,
                            onDisplayModeChange = viewModel::setDisplayMode
                        )
                    }
                }
            }
        }
    }
}
