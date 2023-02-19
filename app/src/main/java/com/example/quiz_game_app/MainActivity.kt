package com.example.quiz_game_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val okButton = findViewById<Button>(R.id.nav_navigator_screen)
        okButton.setOnClickListener {
            val intent = Intent(this, NavigatorActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}