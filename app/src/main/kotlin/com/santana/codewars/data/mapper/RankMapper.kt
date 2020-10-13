package com.santana.codewars.data.mapper

import com.santana.codewars.data.model.RankDTO
import com.santana.codewars.domain.model.RankBO

fun RankDTO.toBO(languageName: String? = null): RankBO =
    RankBO(
        id = id,
        languageName = languageName,
        name = name,
        rank = rank,
        color = color,
        score = score
    )