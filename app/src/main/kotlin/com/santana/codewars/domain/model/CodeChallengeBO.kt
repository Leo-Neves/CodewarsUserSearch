package com.santana.codewars.domain.model

import java.util.*

data class CodeChallengeBO (
    val id: String,
    val name: String,
    val slug: String,
    val description: String,
    val category: String,
    val publishedAt: Date?,
    val approvedAt: Date?,
    val languages: List<String>,
    val tags: List<String>,
    val totalAttempts: Int,
    val totalCompleted: Int,
    val totalStars: Int,
    val voteScore: Int,
    val url: String,
    val unresolvedIssues: Int? = 0
)