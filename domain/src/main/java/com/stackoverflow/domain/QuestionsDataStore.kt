package com.stackoverflow.domain

import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

interface QuestionsDataStore {

    fun getQuestions(): Observable<List<QuestionsEntity>>
}