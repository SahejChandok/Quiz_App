package com.example.quiz_game_app

/**
 * data class that stores question information, such as
 * question, options and correct answer number
 */
data class QuestionModel(val question:String,
                         val option1:String,
                         val option2:String,
                         val option3:String,
                         val answer:Int)