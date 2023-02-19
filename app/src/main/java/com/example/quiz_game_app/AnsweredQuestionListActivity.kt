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
        val homeButton: Button = findViewById(R.id.nav_home_button)
        val parcelableList = this.intent.getParcelableArrayListExtra<Parcelable>("question-list")
        val questions: ArrayList<AnsweredQuestion> = ArrayList()
        if (parcelableList != null) {
            for (parcel in parcelableList) {
                val item: AnsweredQuestion = parcel as AnsweredQuestion
                questions.add(item)
            }
        }
        homeButton.setOnClickListener {
            val intent = Intent(this, NavigatorActivity::class.java)
            finish()
            startActivity(intent)
        }

        listView.adapter = AnsweredQuestionAdapter(this,
           questions
        )
    }
}