package com.santana.codewars.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.santana.codewars.domain.enum.UserOrder
import com.santana.codewars.domain.enum.UserOrder.RANK
import com.santana.codewars.domain.enum.UserOrder.RECENT
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.usecase.FetchUsersUseCase
import com.santana.codewars.domain.usecase.ListUsersUseCase
import com.santana.codewars.state.StateResponse
import com.santana.codewars.state.StateResponse.*
import io.reactivex.rxjava3.schedulers.Schedulers

class FetchUserViewModel @ViewModelInject constructor(
    private val fetchUseCase: FetchUsersUseCase,
    private val listUseCase: ListUsersUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _usersLiveData = MutableLiveData<StateResponse<List<UserBO>>>()
    val usersLiveData get(): LiveData<StateResponse<List<UserBO>>> = _usersLiveData

    private val orderLiveData = MutableLiveData<UserOrder>(UserOrder.RECENT)

    fun fetchUsers(user: String) {
        fetchUseCase.execute(FetchUsersUseCase.Params(user, orderLiveData.value))
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _usersLiveData.postValue(StateLoading())
            }.subscribe({
                _usersLiveData.postValue(StateSuccess(it))
            }, {
                _usersLiveData.postValue(StateError(it))
            })
    }

    fun orderUsersByRank() {
        orderLiveData.value = RANK
        listUsers()
    }

    fun orderUsersByRecent() {
        orderLiveData.value = RECENT
        listUsers()
    }

    private fun listUsers(){
        listUseCase.execute(ListUsersUseCase.Params(orderLiveData.value))
            .subscribeOn(Schedulers.io())
            .subscribe({
                _usersLiveData.postValue(StateSuccess(it))
            }, {
                _usersLiveData.postValue(StateError(it))
            })
    }

}