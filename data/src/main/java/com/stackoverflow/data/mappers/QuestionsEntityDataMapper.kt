package com.stackoverflow.data.mappers

import com.stackoverflow.data.entities.OwnerData
import com.stackoverflow.data.entities.QuestionsData
import com.stackoverflow.domain.common.Mapper
import com.stackoverflow.domain.entities.QuestionsEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionsEntityDataMapper @Inject constructor() : Mapper<QuestionsEntity, QuestionsData>() {

    override fun mapFrom(from: QuestionsEntity): QuestionsData {
        return QuestionsData(
            id = from.id,
            tags = from.tags,
            owner = getOwnerData(from),
            isAnswered = from.is_answered,
            viewCount = from.view_count,
            answerCount = from.answer_count,
            score = from.score,
            creationDate = from.creation_date,
            title = from.title,
            acceptedAnswerId = 0,
            bountyAmount = 0,
            bountyClosesDate = 0,
            lastActivityDate = 0,
            lastEditDate = 0,
            link = "",
            questionId = 0
        )
    }

    private fun getOwnerData(from: QuestionsEntity): OwnerData {
        return OwnerData(
            acceptRate = 0,
            displayName = from.owner.display_name,
            link = "",
            profileImage = "",
            reputation = from.owner.reputation,
            userId = 0,
            userType = ""
        )
    }
}



