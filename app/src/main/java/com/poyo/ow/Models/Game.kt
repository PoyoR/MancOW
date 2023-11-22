package com.poyo.ow.Models

data class Game(
    val gamesLost: Int,
    val gamesPlayed: Int,
    val gamesTied: Int,
    val gamesWon: Int,
    val timePlayed: String
)