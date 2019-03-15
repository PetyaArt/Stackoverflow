package com.stackoverflow.data.mappers

import com.stackoverflow.data.entities.QuestionsData
import com.stackoverflow.domain.common.Mapper
import com.stackoverflow.domain.entities.OwnerEntity
import com.stackoverflow.domain.entities.QuestionsEntity
import javax.inject.Inject

class QuestionsDataEntityMapper @Inject constructor() : Mapper<QuestionsData, QuestionsEntity>() {

    override fun mapFrom(from: QuestionsData): QuestionsEntity {
        return QuestionsEntity(
            id = from.id,
            tags = from.tags,
            owner = getOwnerEntity(from),
            is_answered = from.isAnswered,
            view_count = from.viewCount,
            answer_count = from.answerCount,
            score = from.score,
            creation_date = from.creationDate,
            title = from.title
        )
    }

    private fun getOwnerEntity(from: QuestionsData): OwnerEntity {
        return OwnerEntity(
            display_name = from.owner.displayName,
            reputation = from.owner.reputation
        )
    }
}