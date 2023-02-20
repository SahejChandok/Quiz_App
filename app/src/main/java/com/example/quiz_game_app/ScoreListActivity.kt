package com.example.quiz_game_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ListView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

/**
 * Score List activity is used to display past scores, in reverse chronological order
 * and a navigation button to home screen
 */

class ScoreListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_list)
        val appDatabase = AppDatabase.getDatabase(this)
        val listView: ListView = findViewById<ListView>(R.id.score_list)
        val homeButton: Button = findViewById(R.id.nav_home_button2)
        val scoreList: ArrayList<Score> = appDatabase.scoreDao().getAll() as ArrayList<Score>
        homeButton.setOnClickListener {
            val intent = Intent(this, NavigatorActivity::class.java)
            finish()
            startActivity(intent)
        }
        listView.adapter = ScoreListAdapter(this,
            scoreList
        )



    }
}