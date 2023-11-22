package com.poyo.ow.Models

data class CombatX(
    val barrierDamageDone: Int,
    val damageDone: Int,
    val deaths: Int,
    val eliminations: Int,
    val finalBlows: Int,
    val heroDamageDone: Int,
    val meleeFinalBlows: Int,
    val objectiveKills: Int,
    val objectiveTime: String,
    val quickMeleeAccuracy: String,
    val soloKills: Int,
    val timeSpentOnFire: String,
    val weaponAccuracy: String
)