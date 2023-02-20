package com.example.quiz_game_app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scores ORDER BY scores.id DESC")
    fun getAll(): List<Score>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: Score)
}