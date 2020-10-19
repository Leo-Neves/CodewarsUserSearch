package com.santana.codewars.factory

import com.santana.codewars.data.model.TotalChallengesDTO
import com.santana.codewars.data.model.UserDTO
import com.santana.codewars.factory.RankFactory.mockRankUser

object UserFactory {

    fun mockUser() =
        UserDTO(
            username = "jon",
            name = "Jon",
            honor = 1,
            clan = "Snow",
            leaderboardPosition = 1,
            skills = arrayListOf("leadership"),
            codeChallenges = mockTotalChallenges(),
            ranks = mockRankUser()
        )

    private fun mockTotalChallenges() =
        TotalChallengesDTO(
            totalAuthored = 0,
            totalCompleted = 2
        )


}