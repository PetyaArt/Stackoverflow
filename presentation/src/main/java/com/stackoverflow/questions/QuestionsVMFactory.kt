package com.stackoverflow.questions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.stackoverflow.domain.common.Mapper
import com.stackoverflow.domain.entities.QuestionsEntity
import com.stackoverflow.domain.usecase.GetQuestions
import com.stackoverflow.entities.Question

class QuestionsVMFactory(private val useCase: GetQuestions,
                         private val mapper: Mapper<QuestionsEntity, Question>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuestionsViewModel(useCase, mapper) as T
    }

}