package com.stackoverflow.domain.usecase

import com.stackoverflow.domain.QuestionsCache
import com.stackoverflow.domain.common.Transformer
import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

class SaveQuestions(
    transformer: Transformer<Boolean>,
    private val questionCache: QuestionsCache
) : UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_ENTITY = "anime"
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> {
        val questionEntity = data?.get(PARAM_ENTITY)

        questionEntity?.let {
            return Observable.fromCallable {
                val entity = it as List<QuestionsEntity>
                questionCache.save(entity)
                return@fromCallable true
            }
        } ?: return Observable.error({ IllegalArgumentException("QuestionsEntity must be provided.") })
    }

    fun save(questionsEntity: List<QuestionsEntity>): Observable<Boolean> {
        val data = HashMap<String, List<QuestionsEntity>>()
        data[PARAM_ENTITY] = questionsEntity
        return observable(data)
    }

}