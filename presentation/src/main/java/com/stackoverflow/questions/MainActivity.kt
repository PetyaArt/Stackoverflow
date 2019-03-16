package com.stackoverflow.questions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stackoverflow.R
import com.stackoverflow.utils.ActivityUtils


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var questionsFragment = supportFragmentManager.findFragmentById(R.id.container) as QuestionsFragment?
        if (questionsFragment == null) {
            questionsFragment = QuestionsFragment()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, questionsFragment,
                R.id.container
            )
        }
    }
}
