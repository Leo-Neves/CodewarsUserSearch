package com.santana.codewars.data.repository

import com.santana.codewars.domain.model.ChallengesAuthoredBO
import com.santana.codewars.domain.model.ChallengesCompletedBO
import com.santana.codewars.domain.model.CodeChallengeBO
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.repository.CodewarsRepository

class CodewarsRepositoryImpl: CodewarsRepository {

    override fun getUserInfo(userId: String): UserBO {
        TODO("Not yet implemented")
    }

    override fun getCodeChallenge(challengeId: String): CodeChallengeBO {
        TODO("Not yet implemented")
    }

    override fun getCompletedChallenges(userId: String): List<ChallengesCompletedBO> {
        TODO("Not yet implemented")
    }

    override fun getAuthoredChallenges(userId: String): List<ChallengesAuthoredBO> {
        TODO("Not yet implemented")
    }
}