package com.poyo.ow.Models

data class Average(
    val allDamageDoneAvgPer10Min: Int,
    val barrierDamageDoneAvgPer10Min: Int,
    val deathsAvgPer10Min: Double,
    val eliminationsAvgPer10Min: Double,
    val finalBlowsAvgPer10Min: Double,
    val healingDoneAvgPer10Min: Int,
    val heroDamageDoneAvgPer10Min: Int,
    val objectiveKillsAvgPer10Min: Double,
    val objectiveTimeAvgPer10Min: String,
    val soloKillsAvgPer10Min: Double,
    val timeSpentOnFireAvgPer10Min: String
)