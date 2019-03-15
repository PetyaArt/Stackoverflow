package com.stackoverflow.questions

import com.stackoverflow.entities.Question

data class QuestionsViewState(
    var showLoading: Boolean = true,
    var questions: List<Question>? = null
)