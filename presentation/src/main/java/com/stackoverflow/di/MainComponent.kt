package com.stackoverflow.di

import com.stackoverflow.di.modules.AppModule
import com.stackoverflow.di.modules.DataModule
import com.stackoverflow.di.modules.NetworkModule
import com.stackoverflow.di.questions.QuestionsModule
import com.stackoverflow.di.questions.QuestionsSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface MainComponent {
    fun plus(questionsModule: QuestionsModule): QuestionsSubComponent
}