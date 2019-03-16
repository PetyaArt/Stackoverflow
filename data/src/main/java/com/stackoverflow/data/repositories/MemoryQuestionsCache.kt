package com.stackoverflow.data.repositories

import com.stackoverflow.domain.QuestionsCache
import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

class MemoryQuestionsCache : QuestionsCache {

    private val questions: LinkedHashMap<Int, QuestionsEntity> = LinkedHashMap()

    override fun get(): Observable<List<QuestionsEntity>> {
        return Observable.just(questions.values.toList())
    }

    override fun save(questionsEntity: List<QuestionsEntity>) {
        questionsEntity.forEach { questionEntity -> this.questions[questionEntity.id] = questionEntity}
    }

    override fun isEmpty(): Observable<Boolean> {
        return Observable.fromCallable { questions.isEmpty() }
    }

    override fun clear() {
        questions.clear()
    }
}