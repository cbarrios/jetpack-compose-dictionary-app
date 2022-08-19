package com.lalosapps.dictionaryapp.data.local

import com.lalosapps.dictionaryapp.data.local.dao.WordInfoDao
import com.lalosapps.dictionaryapp.data.local.entity.WordInfoEntity

class LocalWordInfoDataSource(private val wordInfoDao: WordInfoDao) {

    suspend fun insertWordInfos(wordInfos: List<WordInfoEntity>) {
        wordInfoDao.insertWordInfos(wordInfos)
    }

    suspend fun deleteWordInfos(words: List<String>) {
        wordInfoDao.deleteWordInfos(words)
    }

    suspend fun getWordInfos(word: String): List<WordInfoEntity> {
        return wordInfoDao.getWordInfos(word)
    }
}