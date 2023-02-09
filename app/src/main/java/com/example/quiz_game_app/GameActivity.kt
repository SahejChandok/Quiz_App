package com.example.quiz_game_app

import android.content.Intent
import android.media.MediaPlayer
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
import com.example.quiz_game_app.databinding.ActivityMainBinding

class GameActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.glory)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        if(mediaPlayer != null) {
            mediaPlayer.stop()
        }
    }
}