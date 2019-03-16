package com.stackoverflow.questions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.stackoverflow.R
import com.stackoverflow.common.App
import com.stackoverflow.entities.Question
import kotlinx.android.synthetic.main.fragment_question.*
import javax.inject.Inject

class QuestionsFragment : Fragment(), QuestionsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var mPresenter: QuestionsContract.Presenter

    private lateinit var recycledView: RecyclerView
    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).createQuestionsComponent().inject(this)
        mPresenter.takeView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = swipe_layout
        swipeRefreshLayout.setOnRefreshListener(this)
        recycledView = recycler_view
        questionsAdapter = QuestionsAdapter()
        recycledView.layoutManager = LinearLayoutManager(context)
        recycledView.adapter = questionsAdapter

        mPresenter.getQuestions()
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun noConnected() = Toast.makeText(this.context, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()

    override fun setData(questions: List<Question>) {
        questionsAdapter.addQuestions(questions)
    }

    override fun onRefresh() {
        questionsAdapter.clear()
        mPresenter.getQuestions()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.dropView()
        if (activity!!.isFinishing) {
            (activity?.application as App).releaseQuestionsComponent()
        }
    }
}