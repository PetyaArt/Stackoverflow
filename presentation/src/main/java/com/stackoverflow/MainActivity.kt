package com.stackoverflow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.stackoverflow.questions.QuestionsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, QuestionsFragment(), "question")
            .commitNow()
    }
}
