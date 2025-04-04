package com.example.mobile_yvts.data

import kotlinx.coroutines.flow.Flow

class OfflineWordsRepository(
    private val wordDao: WordDao
) : WordsRepository {
    override fun getAllWordsStream(): Flow<List<Word>> = wordDao.getAllWords()
    override fun getWordStream(id: Int): Flow<Word?> = wordDao.getWord(id)
    override suspend fun insertWord(word: Word) = wordDao.insertWord(word)
    override suspend fun deleteWord(word: Word) = wordDao.deleteWord(word)
    override suspend fun updateWord(word: Word) = wordDao.updateWord(word)
}
