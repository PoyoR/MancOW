package com.poyo.ow.Models

data class GameX(
    val gamesLost: Float,
    val gamesPlayed: Float,
    val gamesTied: Float,
    val gamesWon: Float,
    val timePlayed: String,
    val winPercentage: String
)