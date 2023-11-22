package com.poyo.ow.Models

data class HeroSpecific(
    val bioticGrenadeKills: Int,
    val enemiesSlept: Int,
    val enemiesSleptAvgPer10Min: Double,
    val enemiesSleptMostInGame: Int,
    val healingAmplified: Int,
    val healingAmplifiedAvgPer10Min: Int,
    val healingAmplifiedMostInGame: Int,
    val nanoBoostAssists: Int,
    val nanoBoostAssistsAvgPer10Min: Double,
    val nanoBoostAssistsMostInGame: Int,
    val nanoBoostsApplied: Int,
    val nanoBoostsAppliedAvgPer10Min: Double,
    val nanoBoostsAppliedMostInGame: Int,
    val scopedAccuracy: String,
    val scopedAccuracyBestInGame: String,
    val selfHealing: Int,
    val selfHealingAvgPer10Min: Int,
    val selfHealingMostInGame: Int,
    val unscopedAccuracy: String,
    val unscopedAccuracyBestInGame: String
)