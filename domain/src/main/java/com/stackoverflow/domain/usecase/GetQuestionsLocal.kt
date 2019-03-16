package com.stackoverflow.domain.usecase

import com.stackoverflow.domain.QuestionsCache
import com.stackoverflow.domain.common.Transformer
import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

class GetQuestionsLocal(
    transformer: Transformer<List<QuestionsEntity>>,
    private val questionCache: QuestionsCache
) : UseCase<List<QuestionsEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<QuestionsEntity>> {
        return questionCache.get()
    }

}