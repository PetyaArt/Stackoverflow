package com.stackoverflow.data.db

import com.stackoverflow.data.entities.QuestionsData
import com.stackoverflow.domain.QuestionsCache
import com.stackoverflow.domain.common.Mapper
import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

class RoomQuestionsCache(database: QuestionsDatabase,
                         private val entityToDataMapper: Mapper<QuestionsEntity, QuestionsData>,
                         private val dataToEntityMapper: Mapper<QuestionsData, QuestionsEntity>) : QuestionsCache {

    private val dao: QuestionsDao = database.getQuestionsDao()

    override fun save(questionsEntity: List<QuestionsEntity>) {
        dao.saveQuestions(questionsEntity.map {entityToDataMapper.mapFrom(it)})
    }

    override fun get(): Observable<List<QuestionsEntity>> {
        return Observable.fromCallable { dao.getQuestions().map { dataToEntityMapper.mapFrom(it) }}
    }

    override fun inEmpty(): Observable<Boolean> {
        return Observable.fromCallable { dao.getQuestions().isEmpty() }
    }

    override fun clear() {
        dao.clear()
    }
}