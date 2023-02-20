package com.example.quiz_game_app

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.datafaker.Faker
import java.util.*
import java.util.concurrent.TimeUnit


class GameActivity : AppCompatActivity() {
    private val questionList = arrayListOf<String>(
        "How many bones are there in an adult human body?",
        "In The Lion King, who is Simba’s uncle?",
        "The Union Jack is the name of which country’s flag?",
        "Which Nobel Prize did Winston Churchill win?",
        "Which actress played Emily Cooper in 'Emily in Paris'?",
        "Which one of the following is the correct spelling?",
        "Which one of the following is not a character in the cartoon 'The Powerpuff Girls'?",
        "What’s Garfield favourite food?",
        "Which chemical element has Ag as a symbol?",
        "How many elements are there on the periodic table?",
        "In the Big Bang Theory, what is the name of Sheldon and Leonard’s neighbour?",
        "What is the largest continent in size?",
        "Which famous inventor invented the telephone?",
        "What does the Richter scale measure?",
        "What is the longest river in the world?",
        "How many sides has a Hexagon?",
        "Who is the CEO of Amazon?",
        "What was Euclid?",
        "What is the capital of Iraq?",
        "What colour is the 'm' from the McDonald’s logo?"
    )
    private val option1List = arrayListOf<String>(
        "186",
        "Scar",
        "USA",
        "Peace",
        "Lily Collins",
        "Maintanence",
        "Blossom",
        "Lasagna",
        "Gold",
        "100",
        "Penny",
        "America",
        "Thomas Edison",
        "Earthquake intensity",
        "Amazon river",
        "5",
        "Jeff Bezos",
        "Mathematician",
        "Tehran",
        "Red"

    )
    private val option2List = arrayListOf<String>(
        "206",
        "Timon",
        "Australia",
        "Literature",
        "Lily James",
        "Maintanance",
        "Butterfly",
        "Pizza",
        "Iron",
        "118",
        "Patty",
        "Europe",
        "Alexander Graham Bell",
        "Temperature",
        "Congo river",
        "6",
        "Mark Zuckerberg",
        "Poet",
        "Bhagdad",
        "Blue"
    )
    private val option3List = arrayListOf<String>(
        "286",
        "Pumba",
        "UK",
        "History",
        "Gal Gaddot",
        "Maintenance",
        "Bubbles",
        "Burger",
        "Silver",
        "80",
        "Leslie",
        "Asia",
        "Benjamin Franklin",
        "Water level",
        "Nile",
        "7",
        "Elon Musk",
        "Philosopher",
        "Islamabad",
        "Yellow"
    )
    private val answerList = arrayListOf<Int>(
        2,
        1,
        3,
        2,
        1,
        3,
        2,
        1,
        3,
        2,
        1,
        3,
        2,
        1,
        3,
        2,
        1,
        1,
        2,
        3
    )
    private lateinit var appDb: AppDatabase
    lateinit var qlists:ArrayList<QuestionModel>
    private var index:Int=0
    private val faker = Faker()
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
    var questionIndexesToAsk = (0..19).shuffled().take(4)
    private var backPressedTime: Long = 0
    private lateinit var answeredQuestionList: ArrayList<AnsweredQuestion>

    private fun getColor(): Int {
        return (Math.random() * 16777215).toInt() or (0xFF shl 24)
    }

    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()
        appDb = AppDatabase.getDatabase(this)
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
        for(idx in questionList.indices) {
            qlists.add(
                QuestionModel(questionList[idx], option1List[idx], option2List[idx],
                option3List[idx], answerList[idx])
            )
        }
        answeredQuestionList = ArrayList()

        questionModel= qlists[questionIndexesToAsk[count]]
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
                if (count < questionIndexesToAsk.size) {
                    questionModel = qlists[questionIndexesToAsk[count]]
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
        writeData(correctans)
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

    /**
     * generate random user name and write data into the database
     */
    private fun writeData(score: Int) {

        val score = Score(null, score, faker.name().username())
        GlobalScope.launch(Dispatchers.IO) {
            appDb.scoreDao().insert(score)
        }
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
       if(!mediaPlayer) {
           mediaPlayer.stop()
           mediaPlayer.release()
           mediaPlayer = null
       }

   }
}