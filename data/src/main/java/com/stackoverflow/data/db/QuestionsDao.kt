package com.stackoverflow.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.stackoverflow.data.entities.QuestionsData

@Dao
interface QuestionsDao {

    @Query("SELECT * FROM questions")
    fun getQuestions(): List<QuestionsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveQuestions(questionsData: List<QuestionsData>)

    @Query("DELETE FROM questions")
    fun clear()
}