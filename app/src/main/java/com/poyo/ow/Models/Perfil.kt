package com.poyo.ow.Models

data class Perfil(
        val gamesWon: Int? = null,
        val icon: String? = null,
        val level: Int? = null,
        val name: String? = null,
        val prestigeIcon: String? = null,
        val private: Boolean,
        val quickPlayStats: QuickPlayStats? = null,
        //val competitiveStats: CompetitiveStats,
        val ratings: List<Rating>? = null
)