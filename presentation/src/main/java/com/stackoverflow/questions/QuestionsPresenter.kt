package com.stackoverflow.questions

import android.net.ConnectivityManager
import com.stackoverflow.domain.usecase.GetQuestions
import com.stackoverflow.domain.usecase.GetQuestionsLocal
import com.stackoverflow.domain.usecase.SaveQuestions
import com.stackoverflow.mappers.QuestionsDataEntityMapper
import com.stackoverflow.mappers.QuestionsEntityDataMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class QuestionsPresenter(
    private val getQuestions: GetQuestions,
    private val questionsLocal: GetQuestionsLocal,
    private val saveQuestions: SaveQuestions,
    private val network: ConnectivityManager,
    private val entityDataMapper: QuestionsEntityDataMapper,
    private val dataEntityMapper: QuestionsDataEntityMapper
) : QuestionsContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var mView: QuestionsContract.View? = null

    override fun takeView(view: QuestionsContract.View) {
        mView = view
    }

    override fun dropView() {
        clearDisposables()
        mView = null
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun getQuestions() {
        mView?.showLoading()

        if (isConnected()) {
            addDisposable(getQuestions.observable()
                .flatMap { entityDataMapper.observable(it) }
                .subscribe { questions ->
                    addDisposable(dataEntityMapper.observable(questions)
                        .flatMap { lol ->
                            saveQuestions.save(lol) }
                        .subscribe {})

                    mView?.setData(questions)
                    mView?.hideLoading()
                })
        } else {
            mView?.noConnected()
            addDisposable(questionsLocal.observable()
                .flatMap { entityDataMapper.observable(it) }
                .subscribe {

                    mView?.setData(it)
                    mView?.hideLoading()
                })
        }
    }

    private fun isConnected() = network.activeNetworkInfo != null && network.activeNetworkInfo.isConnected

}