package com.santana.codewars.domain.usecase

import com.santana.codewars.domain.model.UserChallengesAuthoredBO
import com.santana.codewars.domain.repository.CodewarsRepository
import io.reactivex.Single

class FetchChallengesAuthoredUseCase(
    val repository: CodewarsRepository
) {

    fun execute(params: Params): Single<UserChallengesAuthoredBO> {
        return repository.getAuthoredChallenges(params.userId, params.page)
    }

    data class Params(val userId: String, val page: Int)
}