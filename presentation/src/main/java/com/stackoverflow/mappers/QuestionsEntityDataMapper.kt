package com.stackoverflow.mappers

import com.stackoverflow.domain.common.Mapper
import com.stackoverflow.domain.entities.QuestionsEntity
import com.stackoverflow.entities.Question
import javax.inject.Inject

class QuestionsEntityDataMapper @Inject constructor() : Mapper<QuestionsEntity, Question>() {

    override fun mapFrom(from: QuestionsEntity): Question {
        return Question(
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