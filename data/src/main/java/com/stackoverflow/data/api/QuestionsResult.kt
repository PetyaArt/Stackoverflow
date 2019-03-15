package com.stackoverflow.data.api

import com.google.gson.annotations.SerializedName
import com.stackoverflow.data.entities.QuestionsData

data class QuestionsResult(

    @SerializedName("has_more")
    val hasMore: Boolean,

    @SerializedName("items")
    val items: List<QuestionsData>,

    @SerializedName("quota_max")
    val quotaMax: Int,

    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)