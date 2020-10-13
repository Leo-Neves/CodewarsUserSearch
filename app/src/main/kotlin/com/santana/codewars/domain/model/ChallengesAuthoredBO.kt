package com.santana.codewars.domain.model

import java.util.*

data class ChallengesAuthoredBO (
    val id: String,
    val name: String,
    val description: String,
    val rank: Int,
    val rankName: String,
    val languages: List<String>,
    val tags: List<String>
)