package com.santana.codewars.ui.userchallenges.completed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santana.codewars.domain.model.ChallengesCompletedBO
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.usecase.FetchChallengesCompletedUseCase
import com.santana.codewars.state.StateResponse
import io.reactivex.schedulers.Schedulers

class ChallengesCompletedViewModel @ViewModelInject constructor(
    private val challengesCompletedUseCase: FetchChallengesCompletedUseCase
) : ViewModel() {

    private val _challengesLiveData = MutableLiveData<StateResponse<List<ChallengesCompletedBO>>>()
    val challengesLiveData get(): LiveData<StateResponse<List<ChallengesCompletedBO>>> = _challengesLiveData
    private lateinit var user: UserBO

    fun selectUser(user: UserBO) {
        this.user = user
    }

    fun fetchChallengesCompleted(page: Int) {
        val disposable = challengesCompletedUseCase
            .execute(FetchChallengesCompletedUseCase.Params(user.username, page))
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _challengesLiveData.postValue(StateResponse.StateLoading())
            }
            .subscribe({ challenges ->
                _challengesLiveData.postValue(
                    if (challenges.data.isNullOrEmpty()) {
                        StateResponse.DataNotFound()
                    } else {
                        StateResponse.StateSuccess(challenges.data)
                    }
                )
            }, { error ->
                _challengesLiveData.postValue(StateResponse.GenericError(error))
            })
    }
}