package com.stackoverflow

interface BasePresenter<T> {
    fun takeView(view: T)
    fun dropView()
}