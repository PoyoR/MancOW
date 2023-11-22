package com.poyo.ow.Models

data class AverageXX(
    val allDamageDoneAvgPer10Min: Int,
    val barrierDamageDoneAvgPer10Min: Int,
    val criticalHitsAvgPer10Min: Double,
    val deathsAvgPer10Min: Double,
    val eliminationsAvgPer10Min: Double,
    val eliminationsPerLife: Int,
    val finalBlowsAvgPer10Min: Double,
    val heroDamageDoneAvgPer10Min: Int,
    val objectiveKillsAvgPer10Min: Double,
    val timeSpentOnFireAvgPer10Min: String
)