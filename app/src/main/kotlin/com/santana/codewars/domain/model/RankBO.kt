package com.santana.codewars.domain.model

data class RankBO (
    val id: String? = null,
    val languageName: String? = null,
    val rank: Int = 0,
    val name: String,
    val color: String,
    val score: Int = 0
)