package com.lalosapps.dictionaryapp.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lalosapps.dictionaryapp.core.util.Resource
import com.lalosapps.dictionaryapp.data.local.LocalWordInfoDataSource
import com.lalosapps.dictionaryapp.data.local.entity.toWordInfo
import com.lalosapps.dictionaryapp.data.remote.RemoteWordInfoDataSource
import com.lalosapps.dictionaryapp.data.remote.dto.ErrorResponse
import com.lalosapps.dictionaryapp.data.remote.dto.toWordInfoEntity
import com.lalosapps.dictionaryapp.domain.model.WordInfo
import com.lalosapps.dictionaryapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordInfoRepositoryImpl(
    private val remoteWordInfoDataSource: RemoteWordInfoDataSource,
    private val localWordInfoDataSource: LocalWordInfoDataSource
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = localWordInfoDataSource.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfos))

        try {
            val response = remoteWordInfoDataSource.getWordInfo(word)
            val remoteWordInfos = response.body()
            if (remoteWordInfos != null) {
                localWordInfoDataSource.deleteWordInfos(remoteWordInfos.map { it.word })
                localWordInfoDataSource.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
            } else {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse =
                    gson.fromJson(response.errorBody()?.charStream(), type)
                emit(
                    Resource.Error(
                        "Error ${response.code()}. ${errorResponse.message}",
                        wordInfos
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString(), wordInfos))
        }

        val newWordInfos = localWordInfoDataSource.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}