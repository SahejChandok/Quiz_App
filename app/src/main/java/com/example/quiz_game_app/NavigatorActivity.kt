package com.example.quiz_game_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

/**
 * Home screen of the app, user can start a new game view past scores
 * or go back to welcome screen
 */
class   NavigatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigator)
        val startGame = findViewById<Button>(R.id.start_game)
        val goBack = findViewById<Button>(R.id.nav_home_screen)
        val viewScores = findViewById<Button>(R.id.view_score)
        // on click navigation handlers
        goBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        viewScores.setOnClickListener {
            val intent = Intent(this, ScoreListActivity::class.java)
            startActivity(intent)
        }
        startGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}