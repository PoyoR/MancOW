package com.poyo.ow.Models

data class BestX(
    val allDamageDoneMostInGame: Float,
    val allDamageDoneMostInLife: Float,
    val barrierDamageDoneMostInGame: Float,
    val eliminationsMostInGame: Float,
    val eliminationsMostInLife: Float,
    val finalBlowsMostInGame: Float,
    val heroDamageDoneMostInGame: Float,
    val heroDamageDoneMostInLife: Float,
    val killsStreakBest: Float,
    val meleeFinalBlowsMostInGame: Float,
    val objectiveKillsMostInGame: Float,
    val objectiveTimeMostInGame: String,
    val soloKillsMostInGame: Float,
    val timeSpentOnFireMostInGame: String,
    val weaponAccuracyBestInGame: String
)