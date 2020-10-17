package com.santana.codewars.data.repository

import com.santana.codewars.data.CodewarsApi
import com.santana.codewars.data.dao.UserDao
import com.santana.codewars.data.mapper.toBO
import com.santana.codewars.data.model.UserDTO
import com.santana.codewars.domain.model.*
import com.santana.codewars.domain.repository.CodewarsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CodewarsRepositoryImpl(
    private val api: CodewarsApi,
    private val userDao: UserDao
): CodewarsRepository {

    override fun getUserInfo(userId: String): Single<UserBO> {
        return api.user(userId).map { it.toBO() }
    }

    override fun getUsers(order: Int): Single<List<UserBO>>{
        return Single.just(userDao.listUsersOrderByRecent())
    }

    override fun saveUser(userBO: UserBO): Single<Any>{
        return Single.just(userDao.insertOrReplace(userBO))
    }

    override fun getCodeChallenge(challengeId: String): Single<CodeChallengeBO> {
        return api.codeChallenge(challengeId).map { it.toBO() }
    }

    override fun getCompletedChallenges(userId: String): Single<UserChallengesCompletedBO> {
        return api.completedChallenges(userId, 0).map { it.toBO() }
    }

    override fun getAuthoredChallenges(userId: String): Single<UserChallengesAuthoredBO> {
        return api.authoredChallenges(userId, 0).map {it.toBO()}
    }
}