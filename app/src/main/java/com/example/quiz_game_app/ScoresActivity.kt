package com.example.quiz_game_app

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.quiz_game_app.databinding.ActivityMainBinding

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