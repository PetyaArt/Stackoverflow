package com.stackoverflow.common

import android.app.Application
import com.stackoverflow.R
import com.stackoverflow.di.DaggerMainComponent
import com.stackoverflow.di.MainComponent
import com.stackoverflow.di.modules.AppModule
import com.stackoverflow.di.modules.DataModule
import com.stackoverflow.di.modules.NetworkModule
import com.stackoverflow.di.questions.QuestionsModule
import com.stackoverflow.di.questions.QuestionsSubComponent

class App: Application() {

    lateinit var mainComponent: MainComponent
    private var questionsComponent: QuestionsSubComponent? = null

    override fun onCreate() {
        super.onCreate()
        initDependencies()
    }

    private fun initDependencies() {
        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(getString(R.string.api_base_url)))
            .dataModule(DataModule())
            .build()
    }

    fun createQuestionsComponent(): QuestionsSubComponent {
        questionsComponent = mainComponent.plus(QuestionsModule())
        return questionsComponent!!
    }
    fun releaseQuestionsComponent() {
        questionsComponent = null
    }
}