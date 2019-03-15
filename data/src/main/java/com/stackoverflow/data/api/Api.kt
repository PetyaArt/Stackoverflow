package com.stackoverflow.data.api

import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("2.2/questions?order=desc&sort=activity&tagged=android&site=stackoverflow")
    fun getQuestions(): Observable<QuestionsResult>
}