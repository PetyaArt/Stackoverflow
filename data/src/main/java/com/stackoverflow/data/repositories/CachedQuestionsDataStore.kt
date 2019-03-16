package com.stackoverflow.data.repositories

import com.stackoverflow.domain.QuestionsCache
import com.stackoverflow.domain.QuestionsDataStore
import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

class CachedQuestionsDataStore(private val questionsCache: QuestionsCache): QuestionsDataStore {

    override fun getQuestions(): Observable<List<QuestionsEntity>> {
        return questionsCache.get()
    }

    fun isEmpty(): Observable<Boolean> {
        return questionsCache.isEmpty()
    }

    fun save(questionsEntity: List<QuestionsEntity>) {
        questionsCache.save(questionsEntity)
    }
}