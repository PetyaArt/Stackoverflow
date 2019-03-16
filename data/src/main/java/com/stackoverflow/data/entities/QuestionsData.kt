package com.stackoverflow.data.entities

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.stackoverflow.data.utils.TagsConverter

@TypeConverters(TagsConverter::class)
@Entity(tableName = "questions")
data class QuestionsData(

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @SerializedName("accepted_answer_id")
    val acceptedAnswerId: Int,

    @SerializedName("answer_count")
    val answerCount: Int,

    @SerializedName("bounty_amount")
    val bountyAmount: Int,

    @SerializedName("bounty_closes_date")
    val bountyClosesDate: Int,

    @SerializedName("creation_date")
    val creationDate: Long,

    @SerializedName("is_answered")
    val isAnswered: Boolean,

    @SerializedName("last_activity_date")
    val lastActivityDate: Int,

    @SerializedName("last_edit_date")
    val lastEditDate: Int,

    @SerializedName("link")
    val link: String,

    @SerializedName("owner")
    @Embedded(prefix = "owner")
    val owner: OwnerData,

    @SerializedName("question_id")
    val questionId: Int,

    @SerializedName("score")
    val score: Int,

    @SerializedName("tags")
    val tags: List<String>,

    @SerializedName("title")
    val title: String,

    @SerializedName("view_count")
    val viewCount: Int
)