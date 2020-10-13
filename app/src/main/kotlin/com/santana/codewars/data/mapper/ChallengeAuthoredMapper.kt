package com.santana.codewars.data.mapper

import com.santana.codewars.data.model.ChallengesAuthoredDTO
import com.santana.codewars.data.model.UserChallengesAuthoredDTO
import com.santana.codewars.domain.model.ChallengesAuthoredBO
import com.santana.codewars.domain.model.UserChallengesAuthoredBO

fun UserChallengesAuthoredDTO.toBO(): UserChallengesAuthoredBO =
    UserChallengesAuthoredBO(
        data = data.map { it.toBO() }
    )

fun ChallengesAuthoredDTO.toBO(): ChallengesAuthoredBO =
    ChallengesAuthoredBO(
        id, name, description, rank, rankName, languages, tags
    )