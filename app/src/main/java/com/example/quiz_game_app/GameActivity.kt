package com.example.quiz_game_app

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import com.example.quiz_game_app.databinding.ActivityMainBinding



class GameActivity : AppCompatActivity() {
    lateinit var qlists:ArrayList<QuestionModel>
    private var index:Int=0
    lateinit var questionModel: QuestionModel
    private var correctans:Int=0
    var count=0
    private var wrongans:Int=0
    lateinit var countDown: TextView
    lateinit var questions: TextView
    lateinit var option1:Button
    lateinit var option2:Button
    lateinit var option3:Button
    var userAnswer: Int = 0
    private var backPressedTime: Long = 0
    private lateinit var answeredQuestionList: ArrayList<AnsweredQuestion>

    private fun getColor(): Int {
        return (Math.random() * 16777215).toInt() or (0xFF shl 24)
    }

    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()
        val activityView = findViewById<ConstraintLayout>(R.id.GameLayout)
        activityView.setBackgroundColor(getColor())
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.glory)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        countDown=findViewById(R.id.countdown)
        questions=findViewById(R.id.questions)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)
        option3=findViewById(R.id.option3)

        qlists=ArrayList()
        answeredQuestionList = ArrayList()
        qlists.add(QuestionModel("1+1?","2","3","4",1))
        qlists.add(QuestionModel("1*1?","2","1","4",2))
        qlists.add(QuestionModel("1-1?","2","3","0",3))
        qlists.add(QuestionModel("2+2?","2","3","4",3))
        qlists.add(QuestionModel("2-3?","2","-1","4",2))
        questionModel= qlists[index]
        setAllQuestions()
        countdown()


    }

    fun countdown(){
        var duration:Long=TimeUnit.SECONDS.toMillis(10)

        object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration: String = String.format(
                    Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    )
                )

                countDown.text = sDuration
            }

            override fun onFinish() {
                var answeredQuestion = AnsweredQuestion(questionModel.question,
                questionModel.option1, questionModel.option2, questionModel.option3,
                    questionModel.answer, userAnswer)
                answeredQuestionList.add(answeredQuestion)
                userAnswer = 0
                count++
                if (count < 5) {
                    index=kotlin.random.Random.nextInt(5)
                    questionModel = qlists[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()

                } else {
                    display()

                }
            }
        }.start()


    }
    private fun correctAnswer(option: Button){
        option.background=getDrawable(R.drawable.right)
        correctans++
    }
    private fun wrongAnswer(option: Button){
        option.background=getDrawable(R.drawable.wrong)
        wrongans++
    }
    private fun display(){
        var intent=Intent(this,ScoresActivity::class.java)
        intent.putExtra("correct",correctans.toString())
        intent.putExtra("total",count.toString())
        intent.putParcelableArrayListExtra("question-list", answeredQuestionList)
        finish()
        startActivity(intent)
    }

    private fun setAllQuestions() {
        questions.text = questionModel.question
        option1.text = questionModel.option1
        option2.text = questionModel.option2
        option3.text = questionModel.option3
    }

    private fun enableButton(){
        option1.isClickable=true
        option2.isClickable=true
        option3.isClickable=true
    }
    private fun disableButton(){
        option1.isClickable=false
        option2.isClickable=false
        option3.isClickable=false
    }
    private fun resetBackground(){
        option1.background=resources.getDrawable(R.drawable.option)
        option2.background=resources.getDrawable(R.drawable.option)
        option3.background=resources.getDrawable(R.drawable.option)
        val color = getColor()
        val view = findViewById<ConstraintLayout>(R.id.GameLayout)
        view.setBackgroundColor(color)
    }

    fun option1Clicked(view: View){
        disableButton()
        userAnswer = 1
        if(questionModel.answer == 1){
            correctAnswer(option1)
        }
        else{
            wrongAnswer(option1)
        }
    }
    fun option2Clicked(view: View){
        disableButton()
        userAnswer = 2
        if(questionModel.answer == 2){
            correctAnswer(option2)
        }
        else{
            wrongAnswer(option2)
        }
    }
    fun option3Clicked(view: View){
        disableButton()
        userAnswer = 3
        if(questionModel.answer == 3){
            correctAnswer(option3)
        }
        else{
            wrongAnswer(option3)
        }
    }



   override fun onStop() {
        super.onStop()
       mediaPlayer.stop()
   }
}