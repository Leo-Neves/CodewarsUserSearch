package com.santana.codewars.data.mapper

import com.santana.codewars.data.model.TotalChallengesDTO
import com.santana.codewars.data.model.UserDTO
import com.santana.codewars.domain.model.TotalChallengesBO
import com.santana.codewars.domain.model.UserBO

fun UserDTO.toBO(): UserBO =
    UserBO(
        username = username,
        name = name,
        clan = clan,
        honor = honor,
        leaderboardPosition = leaderboardPosition,
        skills = skills,
        challenges = codeChallenges.toBO(),
        rankOverall = ranks.overall.toBO(),
        rankLanguages = ranks.languages.map { it.value.toBO(it.key) }
    )

fun TotalChallengesDTO.toBO(): TotalChallengesBO =
    TotalChallengesBO(
        totalCompleted = totalCompleted,
        totalAuthored = totalAuthored
    )