package com.santana.codewars.factory

import com.santana.codewars.data.model.RankDTO
import com.santana.codewars.data.model.RankUserDTO
import com.santana.codewars.domain.model.RankBO

object RankFactory {

    fun mockRank() =
        RankDTO(
            rank = 1,
            name = "3 kyu",
            color = "green",
            score = 1
        )

    fun mockRankUser() =
        RankUserDTO(
            overall = RankDTO(
                rank = 1,
                name = "y jki",
                color = "blue",
                score = 2000
            ),
            languages = mockRankLanguages("java", "ruby", "kotlin")
        )

    fun mockRankLanguages(vararg languages: String): Map<String,RankDTO> {
        return languages.associate { language -> Pair(language, mockRank()) }
    }
}