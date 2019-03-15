package com.stackoverflow.di.questions

import com.stackoverflow.questions.QuestionsFragment
import dagger.Subcomponent

@Subcomponent(modules = [QuestionsModule::class])
interface QuestionsSubComponent {
    fun inject(questionsFragment: QuestionsFragment)
}