package com.stackoverflow.questions

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.stackoverflow.R
import com.stackoverflow.common.App
import kotlinx.android.synthetic.main.fragment_question.*
import javax.inject.Inject

class QuestionsFragment : Fragment() {

    @Inject
    lateinit var factory: QuestionsVMFactory

    private lateinit var viewModel: QuestionsViewModel
    private lateinit var recycledView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).createQuestionsComponent().inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(QuestionsViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.getQuestions()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun handleViewState(state: QuestionsViewState) {
        progressBar.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        state.questions?.let { questionsAdapter.addMovies(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = swipe_layout
        progressBar = progress_bar
        questionsAdapter = QuestionsAdapter()
        recycledView = recycler_view
        recycledView.layoutManager = LinearLayoutManager(context)
        recycledView.adapter = questionsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity?.application as App).releaseQuestionsComponent()
    }
}