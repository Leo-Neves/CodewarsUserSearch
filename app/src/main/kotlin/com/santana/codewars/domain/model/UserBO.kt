package com.santana.codewars.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
class UserBO(
    @PrimaryKey
    val username: String,
    val name: String,
    val honor: Int,
    val clan: String,
    val leaderboardPosition: Int?,
    val skills: List<String>?,
    val updateAt: Date,
    @Ignore
    val rankOverall: RankBO,
    @Ignore
    val rankLanguages: List<RankBO>,
    @Ignore
    val challenges: TotalChallengesBO
)