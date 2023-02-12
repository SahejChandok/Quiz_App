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
    private var backPressedTime: Long = 0
    private var backToast: Toast? = null

    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.glory)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        countDown=findViewById(R.id.countdown)
        questions=findViewById(R.id.questions)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)
        option3=findViewById(R.id.option3)

        qlists=ArrayList()
        qlists.add(QuestionModel("1+1?","2","3","4","2"))
        qlists.add(QuestionModel("1*1?","2","1","4","1"))
        qlists.add(QuestionModel("1-1?","2","3","0","3"))
        qlists.add(QuestionModel("2+2?","2","3","4","3"))
        qlists.add(QuestionModel("2-3?","2","-1","4","2"))




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
                count++
                if (count < 2) {
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
        option.background=getDrawable(R.drawable.right)
        wrongans++
    }
    private fun display(){
        var intent=Intent(this,ScoresActivity::class.java)
        var intent1=Intent(this, DisplayActivity::class.java)
       // setContentView(R.layout.display)
        intent.putExtra("correct",correctans.toString())
        intent.putExtra("total",count.toString())

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
    }

    fun option1Clicked(view: View){
        disableButton()
        if(questionModel.option1==questionModel.answer){
            option1.background=resources.getDrawable(R.drawable.right)


            correctAnswer(option1)

        }
        else{
            wrongAnswer(option1)
        }
    }
    fun option2Clicked(view: View){
        disableButton()
        if(questionModel.option2==questionModel.answer){
            option2.background=resources.getDrawable(R.drawable.right)


            correctAnswer(option2)

        }
        else{
            wrongAnswer(option2)
        }
    }
    fun option3Clicked(view: View){
        disableButton()
        if(questionModel.option3==questionModel.answer){
            option1.background=resources.getDrawable(R.drawable.right)


            correctAnswer(option3)

        }
        else{
            wrongAnswer(option3)
        }
    }



   /*override fun onStop() {
        super.onStop()
        if(mediaPlayer != null) {
            mediaPlayer.stop()
        }
    }*/
}