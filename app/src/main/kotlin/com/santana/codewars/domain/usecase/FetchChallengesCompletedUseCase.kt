package com.santana.codewars.domain.usecase

import com.santana.codewars.domain.model.UserChallengesCompletedBO
import com.santana.codewars.domain.repository.CodewarsRepository
import io.reactivex.Single

class FetchChallengesCompletedUseCase(
    val repository: CodewarsRepository
) {

    fun execute(params: Params): Single<UserChallengesCompletedBO> {
        return repository.getCompletedChallenges(params.userId, params.page)
    }

    data class Params(val userId: String, val page: Int)
}