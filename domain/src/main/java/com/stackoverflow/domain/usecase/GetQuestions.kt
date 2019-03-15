package com.stackoverflow.domain.usecase

import com.stackoverflow.domain.QuestionsRepository
import com.stackoverflow.domain.common.Transformer
import com.stackoverflow.domain.entities.QuestionsEntity
import io.reactivex.Observable

class GetQuestions(transformer: Transformer<List<QuestionsEntity>>,
                   private val questionsRepository: QuestionsRepository): UseCase<List<QuestionsEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<QuestionsEntity>> {
        return questionsRepository.getQuestions()
    }
}