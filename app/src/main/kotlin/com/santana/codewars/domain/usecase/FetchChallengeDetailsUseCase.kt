package com.santana.codewars.domain.usecase

import com.santana.codewars.domain.model.CodeChallengeBO
import com.santana.codewars.domain.repository.CodewarsRepository
import io.reactivex.Single

class FetchChallengeDetailsUseCase(
    val repository: CodewarsRepository
) {

    fun execute(params: Params): Single<CodeChallengeBO> {
        return repository.getCodeChallenge(params.challengeId)
    }

    data class Params(val challengeId: String)
}