package com.santana.codewars.data.mapper

import com.santana.codewars.data.model.CodeChallengeDTO
import com.santana.codewars.domain.model.CodeChallengeBO

fun CodeChallengeDTO.toBO(): CodeChallengeBO =
    CodeChallengeBO(
        id = id,
        name = name,
        slug = slug,
        description = description,
        category = category,
        publishedAt = publishedAt.toDateWithTimezone(),
        approvedAt = approvedAt.toDateWithTimezone(),
        languages = languages,
        tags = tags,
        totalAttempts = totalAttempts,
        totalCompleted = totalCompleted,
        totalStars = totalStars,
        voteScore = voteScore,
        url = url,
        unresolvedIssues = unresolved?.issues
    )