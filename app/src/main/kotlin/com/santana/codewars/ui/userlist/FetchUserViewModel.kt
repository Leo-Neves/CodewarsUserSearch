package com.santana.codewars.ui.userlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santana.codewars.domain.enum.UserOrder.RANK
import com.santana.codewars.domain.enum.UserOrder.RECENT
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.usecase.FetchUsersUseCase
import com.santana.codewars.domain.usecase.ListUsersUseCase
import com.santana.codewars.state.UserResponse
import com.santana.codewars.state.UserResponse.*
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class FetchUserViewModel @ViewModelInject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val listUseCase: ListUsersUseCase,
    private val scheduler: Scheduler
) : ViewModel() {

    private val _usersLiveData = MutableLiveData<UserResponse<List<UserBO>>>()
    val usersLiveData get(): LiveData<UserResponse<List<UserBO>>> = _usersLiveData

    private var listOrder = RECENT

    fun fetchUsers(user: String) {
        val disposable = fetchUsersUseCase.execute(FetchUsersUseCase.Params(user, listOrder))
            .subscribeOn(scheduler)
            .doOnSubscribe {
                _usersLiveData.postValue(UserLoading())
            }.subscribe({
                _usersLiveData.postValue(UserSuccess(it))
            }, {
                if (it is HttpException) {
                    _usersLiveData.postValue(
                        if (it.code() == 404) {
                            UserNotFound(user)
                        } else {
                            NetworkError(it)
                        }
                    )
                } else {
                    _usersLiveData.postValue(GenericError(it))
                }
            })
    }

    fun orderUsersByRank() {
        listOrder = RANK
        listUsers()
    }

    fun orderUsersByRecent() {
        listOrder = RECENT
        listUsers()
    }

    private fun listUsers() {
        listUseCase.execute(ListUsersUseCase.Params(listOrder))
            .subscribeOn(Schedulers.io())
            .subscribe({
                _usersLiveData.postValue(UserSuccess(it))
            }, {
                _usersLiveData.postValue(NetworkError(it))
            })
    }

}