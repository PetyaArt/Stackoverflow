package com.stackoverflow.entities

import com.stackoverflow.domain.entities.OwnerEntity

data class Question(
    val id: Int,
    val tags: List<String>,
    val owner: OwnerEntity,
    val is_answered: Boolean,
    val view_count: Int,
    val answer_count: Int,
    val score: Int,
    val creation_date: Long,
    val title: String
)