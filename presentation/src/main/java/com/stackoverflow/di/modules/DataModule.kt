package com.stackoverflow.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.stackoverflow.data.api.Api
import com.stackoverflow.data.db.QuestionsDatabase
import com.stackoverflow.data.db.RoomQuestionsCache
import com.stackoverflow.data.mappers.QuestionsDataEntityMapper
import com.stackoverflow.data.mappers.QuestionsEntityDataMapper
import com.stackoverflow.data.repositories.CachedQuestionsDataStore
import com.stackoverflow.data.repositories.MemoryQuestionsCache
import com.stackoverflow.data.repositories.QuestionsRepositoryImpl
import com.stackoverflow.data.repositories.RemoteQuestionsDataStore
import com.stackoverflow.di.DI
import com.stackoverflow.domain.QuestionsCache
import com.stackoverflow.domain.QuestionsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): QuestionsDatabase {
        return Room.databaseBuilder(
            context,
            QuestionsDatabase::class.java,
            "questions_db").build()
    }

    @Provides
    @Singleton
    fun provideQuestionsRepository(api: Api, @Named(DI.inMemoryCache) cache: QuestionsCache): QuestionsRepository {

        val cachedMoviesDataStore = CachedQuestionsDataStore(cache)
        val remoteMoviesDataStore = RemoteQuestionsDataStore(api)
        return QuestionsRepositoryImpl(cachedMoviesDataStore, remoteMoviesDataStore)
    }

    @Singleton
    @Provides
    @Named(DI.inMemoryCache)
    fun provideInMemoryQuestionsCache(): QuestionsCache {
        return MemoryQuestionsCache()
    }

    @Singleton
    @Provides
    @Named(DI.questionsCache)
    fun provideQuestionsCache(questionsDatabase: QuestionsDatabase,
                              entityDataMapper: QuestionsEntityDataMapper,
                              dataEntityMapper: QuestionsDataEntityMapper
    ): QuestionsCache {
        return RoomQuestionsCache(questionsDatabase, entityDataMapper, dataEntityMapper)
    }
}