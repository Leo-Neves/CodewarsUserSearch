package com.santana.codewars.domain.model

data class UserChallengesCompletedBO (
    val totalPages: Int,
    val totalItems: Int,
    val data: List<ChallengesCompletedBO>
)