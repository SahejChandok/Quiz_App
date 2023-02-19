package com.example.quiz_game_app

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ScoresActivity : AppCompatActivity() {
     lateinit var correctAns:TextView
     lateinit var totalAns:TextView
     lateinit var score:TextView
     lateinit var output:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        supportActionBar?.hide()

        correctAns=findViewById(R.id.correctAns)
        totalAns=findViewById(R.id.totalAns)
        score=findViewById(R.id.score)
        output=findViewById(R.id.output)

        val intent=intent
        val NoOfCorrectAns=intent.getStringExtra("correct")
        val NoOfTotal = intent.getStringExtra("total")
        val answeredQuestionList = intent.getParcelableArrayListExtra<Parcelable>("question-list")
        val viewAnswersButton = findViewById<Button>(R.id.view_answers_btn)
        viewAnswersButton.setOnClickListener {
            val answeredQuestionsActivityIntent = Intent(this,
                AnsweredQuestionListActivity::class.java)
            answeredQuestionsActivityIntent.putParcelableArrayListExtra("question-list",
                answeredQuestionList)
            finish()
            startActivity(answeredQuestionsActivityIntent)
        }
        correctAns.text=NoOfCorrectAns
        totalAns.text=NoOfTotal

        val per= (NoOfCorrectAns?.toFloat()?.div(NoOfTotal?.toFloat()!!))?.times(100)

        if(per !=null){
            when{
                50 <= per && per <=99 ->{
                    score.text="GOOD"
                    output.background=resources.getDrawable(R.drawable.option)
                }
                per>=100 ->{
                    score.text="AWESOME"
                    output.background=resources.getDrawable(R.drawable.option)
                }
                per<50 ->{
                    score.text="BAD"
                    output.background=resources.getDrawable(R.drawable.option)
                }

            }
        }
    }

    override fun onBackPressed() {
        var intent=Intent(this,NavigatorActivity::class.java)
        startActivity(intent)
    }
}