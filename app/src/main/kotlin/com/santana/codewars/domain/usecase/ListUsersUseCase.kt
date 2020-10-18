package com.santana.codewars.domain.usecase

import com.santana.codewars.data.model.UserDTO
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.repository.CodewarsRepository
import io.reactivex.rxjava3.core.Single

class ListUsersUseCase(
    val repository: CodewarsRepository
) {

    fun execute(params: Params): Single<List<UserBO>> {
        return repository.getUserInfo(params.userId).flatMap {
            repository.saveUser(it).flatMap {
                repository.getUsers(params.order)
            }
        }
    }

    data class Params(val userId: String, val order: Int)
}