package com.santana.codewars.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.usecase.ListUsersUseCase
import com.santana.codewars.domain.usecase.ListUsersUseCase.Params
import com.santana.codewars.state.StateResponse
import com.santana.codewars.state.StateResponse.*

class FetchUserViewModel @ViewModelInject constructor(
    private val useCase: ListUsersUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _liveData = MutableLiveData<StateResponse<List<UserBO>>>()
    val liveData get(): LiveData<StateResponse<List<UserBO>>> = _liveData

    fun fetchUsers(user: String) {
        useCase.execute(Params(user, 0)).doOnSubscribe {
            _liveData.value = StateLoading()
        }.subscribe({
            _liveData.value = StateSuccess(it)
        }, {
            _liveData.value = StateError(it)
        })
    }

}