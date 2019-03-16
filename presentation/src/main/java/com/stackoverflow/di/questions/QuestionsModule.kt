package com.stackoverflow.di.questions

import android.net.ConnectivityManager
import com.stackoverflow.common.ASyncTransformer
import com.stackoverflow.di.DI
import com.stackoverflow.domain.QuestionsCache
import com.stackoverflow.domain.QuestionsRepository
import com.stackoverflow.domain.usecase.GetQuestions
import com.stackoverflow.domain.usecase.GetQuestionsLocal
import com.stackoverflow.domain.usecase.SaveQuestions
import com.stackoverflow.mappers.QuestionsDataEntityMapper
import com.stackoverflow.mappers.QuestionsEntityDataMapper
import com.stackoverflow.questions.QuestionsContract
import com.stackoverflow.questions.QuestionsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@QuestionsScope
@Module
class QuestionsModule {

    @Provides
    fun provideGetQuestions(questionsRepository: QuestionsRepository): GetQuestions {
        return GetQuestions(ASyncTransformer(), questionsRepository)
    }

    @Provides
    fun provideGetQuestionsLocal(@Named(DI.questionsCache) questionsCache: QuestionsCache): GetQuestionsLocal {
        return GetQuestionsLocal(ASyncTransformer(), questionsCache)
    }

    @Provides
    fun provideSaveQuestions(@Named(DI.questionsCache) questionsCache: QuestionsCache): SaveQuestions {
        return SaveQuestions(ASyncTransformer(), questionsCache)
    }

    @Provides
    fun provideQuestionsPresenter(
        getQuestions: GetQuestions,
        getQuestionsLocal: GetQuestionsLocal,
        saveQuestions: SaveQuestions,
        network: ConnectivityManager,
        entityDataMapper: QuestionsEntityDataMapper,
        dataEntityMapper: QuestionsDataEntityMapper
    ): QuestionsContract.Presenter {
        return QuestionsPresenter(getQuestions, getQuestionsLocal, saveQuestions, network, entityDataMapper, dataEntityMapper)
    }
}