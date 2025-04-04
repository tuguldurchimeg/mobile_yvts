package com.example.mobile_yvts.data

import android.content.Context

class AppContainer(context: Context) {
    private val database = WordDatabase.getDatabase(context)
    val wordsRepository: WordsRepository = OfflineWordsRepository(database.wordDao())
}
