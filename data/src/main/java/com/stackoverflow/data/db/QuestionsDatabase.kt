package com.stackoverflow.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.stackoverflow.data.entities.QuestionsData

@Database(entities = [QuestionsData::class], version = 1)
abstract class QuestionsDatabase: RoomDatabase() {
    abstract fun getQuestionsDao(): QuestionsDao
}