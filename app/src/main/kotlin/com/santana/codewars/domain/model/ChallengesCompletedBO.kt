package com.santana.codewars.domain.model

import java.util.*

data class ChallengesCompletedBO (
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: Date,
    val completedLanguages: List<String>
)