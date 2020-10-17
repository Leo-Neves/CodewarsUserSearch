package com.santana.codewars.domain.usecase

import com.santana.codewars.domain.enum.UserOrder
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.repository.CodewarsRepository
import io.reactivex.rxjava3.core.Single

class ListUsersUseCase(
    val repository: CodewarsRepository
) {

    fun execute(params: Params): Single<List<UserBO>> {
        return repository.getUsers(params.order)
    }

    data class Params(val order: UserOrder?)
}