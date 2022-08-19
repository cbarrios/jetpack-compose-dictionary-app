package com.lalosapps.dictionaryapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lalosapps.dictionaryapp.data.local.dao.WordInfoDao
import com.lalosapps.dictionaryapp.data.local.entity.Converters
import com.lalosapps.dictionaryapp.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract val wordInfoDao: WordInfoDao
}