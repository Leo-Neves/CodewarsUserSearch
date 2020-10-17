package com.santana.codewars.domain.repository

import com.santana.codewars.domain.model.*
import io.reactivex.rxjava3.core.Single

interface CodewarsRepository {
    fun getUserInfo(userId: String): Single<UserBO>
    fun getUsers(order: Int): Single<List<UserBO>>
    fun saveUser(userBO: UserBO): Single<Any>
    fun getCodeChallenge(challengeId: String): Single<CodeChallengeBO>
    fun getCompletedChallenges(userId: String): Single<UserChallengesCompletedBO>
    fun getAuthoredChallenges(userId: String): Single<UserChallengesAuthoredBO>
}