package com.example.quiz_game_app

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ScoreListAdapter(private val context: Activity, private val arrayList: ArrayList<Score>) : ArrayAdapter<Score>(context,
    R.layout.activity_score_list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_score_list_item, null)

        val username: TextView = view.findViewById(R.id.username)
        val score: TextView = view.findViewById(R.id.score_text)

        val usernameText: String? = arrayList[position].username
        val scoreText: Int? = arrayList[position].score

        username.text = usernameText
        score.text = scoreText.toString()
        return view

    }
}