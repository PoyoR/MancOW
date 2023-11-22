package com.poyo.ow.Models

data class Profile(
    val competitiveStats: CompetitiveStats? = null,
    val endorsement: Int? = null,
    val endorsementIcon: String? = null,
    val gamesWon: Int? = null,
    val icon: String? = null,
    val level: Int? = null,
    val levelIcon: String? = null,
    val name: String? = null,
    val prestige: Int? = null,
    val prestigeIcon: String? = null,
    val `private`: Boolean? = null,
    val quickPlayStats: QuickPlayStats,
    val ratings: List<Rating>? = null
)