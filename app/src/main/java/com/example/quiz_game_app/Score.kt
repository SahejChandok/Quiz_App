package com.example.quiz_game_app

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class that is used to access the scores from the database
 */
@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val score: Int?,
    val username: String?
)
