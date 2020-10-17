package com.santana.codewars.domain.usecase

import com.santana.codewars.domain.enum.UserOrder
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.repository.CodewarsRepository
import com.santana.codewars.domain.usecase.ListUsersUseCase.Params
import io.reactivex.rxjava3.core.Single

class FetchUsersUseCase(
    val repository: CodewarsRepository,
    val listUsersUseCase: ListUsersUseCase
) {

    fun execute(params: Params): Single<List<UserBO>> {
        return repository.getUserInfo(params.userId).flatMap {
            repository.saveUser(it).flatMap {
                listUsersUseCase.execute(Params(params.order))
            }
        }
    }

    data class Params(val userId: String, val order: UserOrder?)
}