package com.stackoverflow.data.repositories

import com.stackoverflow.data.api.Api
import com.stackoverflow.data.db.QuestionsDatabase
import com.stackoverflow.data.mappers.QuestionsDataEntityMapper
import com.stackoverflow.domain.QuestionsDataStore
import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

class RemoteQuestionsDataStore(private val api: Api) : QuestionsDataStore {

    private val questionsDataMapper = QuestionsDataEntityMapper()

    override fun getQuestions(): Observable<List<QuestionsEntity>> {
        return api.getQuestions().map { results ->
            results.items.map { questionsDataMapper.mapFrom(it) }
        }
    }
}