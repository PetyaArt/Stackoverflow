package com.stackoverflow.questions

import android.arch.lifecycle.MutableLiveData
import com.stackoverflow.common.BaseViewModel
import com.stackoverflow.common.SingleLiveEvent
import com.stackoverflow.domain.common.Mapper
import com.stackoverflow.domain.entities.QuestionsEntity
import com.stackoverflow.domain.usecase.GetQuestions
import com.stackoverflow.entities.Question

class QuestionsViewModel(private val getQuestions: GetQuestions,
                         private val questionEntityDataMapper: Mapper<QuestionsEntity, Question>) : BaseViewModel() {

    var viewState: MutableLiveData<QuestionsViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        viewState.value = QuestionsViewState()
    }

    fun getQuestions() {
        addDisposable(getQuestions.observable()
            .flatMap { questionEntityDataMapper.observable(it) }
            .subscribe({questions ->
                viewState.value?.let {
                    val newState = this.viewState.value?.copy(showLoading = false, questions = questions)
                    this.viewState.value = newState
                    this.errorState.value = null
                }
            }, {
                viewState.value = viewState.value?.copy(showLoading = false)
                errorState.value = it
            }))
    }
}