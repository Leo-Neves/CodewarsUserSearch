package com.santana.codewars.data.mapper

import com.santana.codewars.data.model.ChallengesCompletedDTO
import com.santana.codewars.data.model.UserChallengesCompletedDTO
import com.santana.codewars.domain.model.ChallengesCompletedBO
import com.santana.codewars.domain.model.UserChallengesCompletedBO

fun UserChallengesCompletedDTO.toBO(): UserChallengesCompletedBO =
    UserChallengesCompletedBO(
        totalPages = totalPages,
        totalItems = totalItems,
        data = data.map { it.toBO() }
    )

fun ChallengesCompletedDTO.toBO(): ChallengesCompletedBO =
    ChallengesCompletedBO(
        id, name, slug, completedAt, completedLanguages
    )