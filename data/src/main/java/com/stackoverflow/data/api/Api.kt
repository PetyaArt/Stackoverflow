package com.stackoverflow.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("2.2/questions?pagesize=100&order=desc&sort=activity&tagged=android&site=stackoverflow")
    fun getQuestions(): Observable<QuestionsResult>
}