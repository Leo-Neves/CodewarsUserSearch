package com.santana.codewars.data.mapper

import com.santana.codewars.data.model.CodeChallengeDTO
import com.santana.codewars.domain.model.CodeChallengeBO

fun CodeChallengeDTO.toBO(): CodeChallengeBO =
    CodeChallengeBO(
        id = id,
        name = name,
        slug = slug,
        category = category,
        publishedAt = publishedAt.toDateWithTimezone(),
        approvedAt = approvedAt.toDateWithTimezone(),
        languages = languages,
        url = url
    )