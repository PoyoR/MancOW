package com.poyo.ow.Models

data class HeroSpecificX(
    val bobKills: Int,
    val bobKillsAvgPer10Min: Double,
    val bobKillsMostInGame: Int,
    val dynamiteKills: Int,
    val dynamiteKillsAvgPer10Min: Double,
    val dynamiteKillsMostInGame: Int,
    val scopedAccuracy: String,
    val scopedAccuracyBestInGame: String,
    val scopedCriticalHits: Int,
    val scopedCriticalHitsAccuracy: String,
    val scopedCriticalHitsAvgPer10Min: Double,
    val scopedCriticalHitsMostInGame: Int
)