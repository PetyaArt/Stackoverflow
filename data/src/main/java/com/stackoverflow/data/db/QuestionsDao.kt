package com.stackoverflow.data.db

import android.arch.persistence.room.*
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