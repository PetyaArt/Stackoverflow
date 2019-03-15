package com.stackoverflow.domain

import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable


interface QuestionsCache {

    fun clear()
    fun save(questionsEntity: List<QuestionsEntity>)
    fun get(): Observable<List<QuestionsEntity>>
    fun inEmpty(): Observable<Boolean>
}