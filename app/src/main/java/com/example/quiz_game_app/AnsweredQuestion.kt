package com.example.quiz_game_app

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnsweredQuestion(val question:String,
                         val option1:String,
                         val option2:String,
                         val option3:String,
                         val answer:String,
                         val user_answer: String?,
                         val is_correct: Boolean?) : Parcelable
