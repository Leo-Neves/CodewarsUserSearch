package com.santana.codewars.domain.model

data class UserBO(
    val username: String,
    val name: String,
    val honor: Int,
    val clan: String,
    val leaderboardPosition: Int?,
    val skills: List<String>?,
    val rankOverall: RankBO,
    val rankLanguages: List<RankBO>
)