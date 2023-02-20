package com.example.quiz_game_app

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * This adaptor supplies the data to the listView,
 * here we read the data from the database.
 */
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
        val userAnswer = arrayList[position].userAnswer
        val isCorrect = answer == userAnswer

        question_text.text = question
        option_1_text.text = option1
        option_2_text.text = option2
        option_3_text.text = option3
        // used for highlighting the correct answer
        if(answer == 1 ) {
            option_1_text.setBackgroundResource(R.drawable.right)
        } else if(answer == 2) {
            option_2_text.setBackgroundResource(R.drawable.right)
        } else {
            option_3_text.setBackgroundResource(R.drawable.right)
        }
        // if the user gave an incorrect answer, the same will be
        // highlighted as well
        if(!isCorrect) {
            if(userAnswer == 1) {
                option_1_text.setBackgroundResource(R.drawable.wrong)
            } else if(userAnswer == 2) {
                option_2_text.setBackgroundResource(R.drawable.wrong)
            } else if(userAnswer == 3 ) {
                option_3_text.setBackgroundResource(R.drawable.wrong)
            }
        }
        return view

    }
}