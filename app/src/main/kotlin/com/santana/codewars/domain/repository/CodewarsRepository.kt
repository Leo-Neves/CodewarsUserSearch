package com.santana.codewars.domain.repository

import com.santana.codewars.domain.model.ChallengesAuthoredBO
import com.santana.codewars.domain.model.ChallengesCompletedBO
import com.santana.codewars.domain.model.CodeChallengeBO
import com.santana.codewars.domain.model.UserBO

interface CodewarsRepository {
    fun getUserInfo(userId: String): UserBO
    fun getCodeChallenge(challengeId: String): CodeChallengeBO
    fun getCompletedChallenges(userId: String): List<ChallengesCompletedBO>
    fun getAuthoredChallenges(userId: String): List<ChallengesAuthoredBO>
}