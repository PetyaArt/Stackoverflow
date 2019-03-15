package com.stackoverflow.di.questions

import com.stackoverflow.QuestionsEntityDataMapper
import com.stackoverflow.common.ASyncTransformer
import com.stackoverflow.domain.QuestionsRepository
import com.stackoverflow.domain.usecase.GetQuestions
import com.stackoverflow.questions.QuestionsVMFactory
import dagger.Module
import dagger.Provides

@QuestionsScope
@Module
class QuestionsModule {

    @Provides
    fun provideGetPopularMoviesUseCase(questionsRepository: QuestionsRepository): GetQuestions {
        return GetQuestions(ASyncTransformer(), questionsRepository)
    }

    @Provides
    fun providePopularMoviesVMFactory(useCase: GetQuestions, entityDataMapper: QuestionsEntityDataMapper): QuestionsVMFactory {
        return QuestionsVMFactory(useCase, entityDataMapper)
    }
}