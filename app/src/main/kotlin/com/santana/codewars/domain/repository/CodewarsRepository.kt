package com.santana.codewars.domain.repository

import com.santana.codewars.domain.enum.UserOrder
import com.santana.codewars.domain.model.*
import io.reactivex.Single

interface CodewarsRepository {
    fun getUserInfo(userId: String): Single<UserBO>
    fun getUsers(order: UserOrder?): Single<List<UserBO>>
    fun saveUser(userBO: UserBO): Single<Any>
    fun getCodeChallenge(challengeId: String): Single<CodeChallengeBO>
    fun getCompletedChallenges(userId: String, page: Int): Single<UserChallengesCompletedBO>
    fun getAuthoredChallenges(userId: String, page: Int): Single<UserChallengesAuthoredBO>
}