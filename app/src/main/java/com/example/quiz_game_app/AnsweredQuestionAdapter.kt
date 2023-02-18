package com.example.quiz_game_app

import android.app.Activity
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AnsweredQuestionAdapter(private val context: Activity, private val arrayList: ArrayList<AnsweredQuestion>) : ArrayAdapter<AnsweredQuestion>(context,
    R.layout.answered_question_list_item_view, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.answered_question_list_item_view, null)

        val question_text: TextView = view.findViewById(R.id.question_item_text)
        val option_1_text: TextView = view.findViewById(R.id.option_1_text)
        val option_2_text: TextView = view.findViewById(R.id.option_2_text)
        val option_3_text: TextView = view.findViewById(R.id.option_3_text)
        val option1: String = arrayList[position].option1
        val option2: String = arrayList[position].option2
        val option3: String = arrayList[position].option3
        val question: String = arrayList[position].question
        val answer = arrayList[position].answer
        val userAnswer = arrayList[position].user_answer
        val isCorrect = arrayList[position].is_correct

        question_text.text = question
        option_1_text.text = option1
        option_2_text.text = option2
        option_3_text.text = option3


        if(answer == option1 ) {
            option_1_text.setTextColor(Color.GREEN)
            option_1_text.typeface = Typeface.DEFAULT_BOLD
        } else if(answer == option2) {
            option_2_text.setTextColor(Color.GREEN)
            option_2_text.typeface = Typeface.DEFAULT_BOLD
        } else {
            option_3_text.setTextColor(Color.GREEN)
            option_3_text.typeface = Typeface.DEFAULT_BOLD
        }
        if(isCorrect == false) {
            if(userAnswer == option1) {
                option_1_text.setTextColor(Color.RED)
                option_1_text.typeface = Typeface.DEFAULT_BOLD
            } else if(userAnswer == option2) {
                option_2_text.setTextColor(Color.RED)
                option_2_text.typeface = Typeface.DEFAULT_BOLD
            } else if(userAnswer == option3 ) {
                option_3_text.setTextColor(Color.RED)
                option_3_text.typeface = Typeface.DEFAULT_BOLD
            }
        }

        return view

    }
}