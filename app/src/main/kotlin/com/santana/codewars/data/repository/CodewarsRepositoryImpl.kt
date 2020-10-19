package com.santana.codewars.data.repository

import com.santana.codewars.data.CodewarsApi
import com.santana.codewars.data.dao.UserDao
import com.santana.codewars.data.mapper.toBO
import com.santana.codewars.domain.enum.UserOrder
import com.santana.codewars.domain.enum.UserOrder.RECENT
import com.santana.codewars.domain.model.CodeChallengeBO
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.model.UserChallengesAuthoredBO
import com.santana.codewars.domain.model.UserChallengesCompletedBO
import com.santana.codewars.domain.repository.CodewarsRepository
import io.reactivex.Single

class CodewarsRepositoryImpl(
    private val api: CodewarsApi,
    private val userDao: UserDao
) : CodewarsRepository {

    override fun getUserInfo(userId: String): Single<UserBO> {
        return api.user(userId).map { it.toBO() }
    }

    override fun getUsers(order: UserOrder?): Single<List<UserBO>> {
        return if (order == RECENT) userDao.listUsersOrderByRecent()
        else userDao.listUsersOrderByRank()
    }

    override fun saveUser(userBO: UserBO): Single<Any> {
        return Single.just(userDao.insertOrReplace(userBO))
    }

    override fun getCodeChallenge(challengeId: String): Single<CodeChallengeBO> {
        return api.codeChallenge(challengeId).map { it.toBO() }
    }

    override fun getCompletedChallenges(userId: String, page: Int): Single<UserChallengesCompletedBO> {
        return api.completedChallenges(userId, page).map { it.toBO() }
    }

    override fun getAuthoredChallenges(userId: String, page: Int): Single<UserChallengesAuthoredBO> {
        return api.authoredChallenges(userId, page).map { it.toBO() }
    }
}