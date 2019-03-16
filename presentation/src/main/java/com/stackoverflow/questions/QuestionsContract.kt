package com.stackoverflow.questions

import com.stackoverflow.BasePresenter
import com.stackoverflow.BaseView
import com.stackoverflow.entities.Question

interface QuestionsContract {

    interface View : BaseView<Presenter> {

        fun showLoading()

        fun hideLoading()

        fun noConnected()

        fun setData(questions: List<Question>)
    }

    interface Presenter : BasePresenter<View> {

        fun getQuestions()
    }
}