package com.lalosapps.dictionaryapp.core.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.lalosapps.dictionaryapp.core.util.Constants
import com.lalosapps.dictionaryapp.core.util.json.GsonParser
import com.lalosapps.dictionaryapp.data.local.LocalWordInfoDataSource
import com.lalosapps.dictionaryapp.data.local.dao.WordInfoDao
import com.lalosapps.dictionaryapp.data.local.db.WordInfoDatabase
import com.lalosapps.dictionaryapp.data.remote.RemoteWordInfoDataSource
import com.lalosapps.dictionaryapp.data.remote.api.DictionaryApi
import com.lalosapps.dictionaryapp.data.repository.WordInfoRepositoryImpl
import com.lalosapps.dictionaryapp.domain.repository.WordInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideWordInfoDatabase(@ApplicationContext context: Context): WordInfoDatabase =
        Room.databaseBuilder(
            context,
            WordInfoDatabase::class.java,
            Constants.WORD_INFO_DATABASE_NAME
        ).addTypeConverter(GsonParser(Gson())).build()

    @Provides
    @Singleton
    fun provideWordInfoDao(db: WordInfoDatabase): WordInfoDao = db.wordInfoDao

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.DICTIONARY_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDictionaryApi(retrofit: Retrofit): DictionaryApi =
        retrofit.create(DictionaryApi::class.java)

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        remoteWordInfoDataSource: RemoteWordInfoDataSource,
        localWordInfoDataSource: LocalWordInfoDataSource
    ): WordInfoRepository =
        WordInfoRepositoryImpl(remoteWordInfoDataSource, localWordInfoDataSource)
}