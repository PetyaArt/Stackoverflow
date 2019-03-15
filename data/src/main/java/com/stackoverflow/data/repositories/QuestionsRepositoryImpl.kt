package com.stackoverflow.data.repositories

import com.stackoverflow.domain.QuestionsRepository
import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

class QuestionsRepositoryImpl(private val cachedDataStore: CachedQuestionsDataStore,
                              private val remoteDataStore: RemoteQuestionsDataStore) : QuestionsRepository {

    override fun getQuestions(): Observable<List<QuestionsEntity>> {

        return cachedDataStore.isEmpty().flatMap { empty ->
            if (!empty) {
                return@flatMap cachedDataStore.getQuestions()
            } else {
                return@flatMap remoteDataStore.getQuestions()
                    .doOnNext { questions ->
                        cachedDataStore.save(questions)
                    }
            }
        }
    }
}