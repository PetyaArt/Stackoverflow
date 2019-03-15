package com.stackoverflow.domain

import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

interface QuestionsRepository {
    fun getQuestions(): Observable<List<QuestionsEntity>>
}