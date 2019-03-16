package com.stackoverflow.mappers

import com.stackoverflow.domain.common.Mapper
import com.stackoverflow.domain.entities.QuestionsEntity
import com.stackoverflow.entities.Question
import javax.inject.Inject

class QuestionsDataEntityMapper @Inject constructor() : Mapper<Question, QuestionsEntity>() {

    override fun mapFrom(from: Question): QuestionsEntity {
        return QuestionsEntity(
            id = from.id,
            tags = from.tags,
            owner = from.owner,
            is_answered = from.is_answered,
            view_count = from.view_count,
            answer_count = from.answer_count,
            score = from.score,
            creation_date = from.creation_date,
            title = from.title
        )
    }
}