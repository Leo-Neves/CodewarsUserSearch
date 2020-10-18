package com.santana.codewars.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.santana.codewars.domain.typeconverter.ListRankConverter
import com.santana.codewars.domain.typeconverter.RankConverter
import com.santana.codewars.domain.typeconverter.TotalChallengeConverter
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
    val updateAt: Long,
    val rankOverall: RankBO,
    val rankLanguages: List<RankBO>,
    val challenges: TotalChallengesBO
)