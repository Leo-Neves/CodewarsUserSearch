package com.santana.codewars.data.mapper

import com.santana.codewars.data.model.UserDTO
import com.santana.codewars.domain.model.UserBO

fun UserDTO.toBO(): UserBO =
    UserBO(
        username = username,
        name = name,
        clan = clan,
        honor = honor,
        leaderboardPosition = leaderboardPosition,
        skills = skills
    )