package com.santana.codewars.domain.model

import java.util.*

data class CodeChallengeBO (
    val id: String,
    val name: String,
    val slug: String,
    val category: String,
    val publishedAt: Date?,
    val approvedAt: Date?,
    val languages: List<String>,
    val url: String
)