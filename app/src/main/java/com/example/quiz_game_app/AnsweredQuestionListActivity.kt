package com.example.quiz_game_app

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ListView
import kotlin.collections.ArrayList


class AnsweredQuestionListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answered_question_list)
        val listView: ListView = findViewById<ListView>(R.id.answered_question_list)
        val parcelableList = this.intent.getParcelableArrayListExtra<Parcelable>("question-list")
        val questions: ArrayList<AnsweredQuestion> = ArrayList()
        if (parcelableList != null) {
            for (parcel in parcelableList) {
                questions.add(parcel as AnsweredQuestion)
            }
        }

        listView.adapter = AnsweredQuestionAdapter(this,
           questions
        )
    }
}