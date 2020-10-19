package com.santana.codewars.factory

import com.santana.codewars.data.model.*
import com.santana.codewars.domain.model.UserChallengesAuthoredBO
import com.santana.codewars.factory.RankFactory.mockRank
import java.util.*

object ChallengeFactory {

    fun mockChallengeAuthored() =
        UserChallengesAuthoredDTO(
            data = listOf(
                ChallengesAuthoredDTO(
                    id = "id",
                    name = "binary search",
                    description = "binary search in cpp",
                    rank = 2,
                    rankName = "3 yut",
                    languages = listOf("cpp"),
                    tags = listOf("search", "performance")
                )
            )
        )

    fun mockChallengeCompleted() =
        UserChallengesCompletedDTO(
            totalPages = 1,
            totalItems = 1,
            data = listOf(
                ChallengesCompletedDTO(
                    id = "id",
                    name = "airport way",
                    slug = "fastest way to go to airport",
                    completedAt = Date(),
                    completedLanguages = listOf("fortran")
                )
            )
        )

    fun mockCodeChallenges() =
        CodeChallengeDTO(
            id = "id",
            name = "remove saturation",
            slug = "remove saturation of pixels",
            tags = listOf("bitmap"),
            languages = listOf("cpp","c"),
            rank = mockRank(),
            description = "pixels saturation removed",
            category = "image processing",
            publishedAt = "2020-19-01 00:00:00",
            createdBy = UsernameDTO("Jon","http://jon.com"),
            approvedBy = UsernameDTO("Jon","http://jon.com"),
            approvedAt = "2020-19-01 00:00:00",
            unresolved = UnresolvedIssuesDTO(1, 0),
            voteScore = 0,
            totalStars = 0,
            totalCompleted = 1,
            totalAttempts = 1,
            url = "http://challenge"

        )
}