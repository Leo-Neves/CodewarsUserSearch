package com.santana.codewars.ui.challengedetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santana.codewars.domain.model.CodeChallengeBO
import com.santana.codewars.domain.usecase.FetchChallengeDetailsUseCase
import com.santana.codewars.state.StateResponse
import io.reactivex.schedulers.Schedulers

class ChallengeDetailsViewModel @ViewModelInject constructor(
    private val fetchChallengeDetailsUseCase: FetchChallengeDetailsUseCase
): ViewModel(){

    private val _challengeLiveData = MutableLiveData<StateResponse<CodeChallengeBO>>()
    val challengeLiveData get(): LiveData<StateResponse<CodeChallengeBO>> = _challengeLiveData
    private lateinit var challengeId: String

    fun selectChallenge(challengeId: String) {
        this.challengeId = challengeId
    }

    fun fetchChallengeDetails() {
        val disposable = fetchChallengeDetailsUseCase
            .execute(FetchChallengeDetailsUseCase.Params(challengeId))
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                _challengeLiveData.postValue(StateResponse.StateLoading())
            }
            .subscribe({ challenge ->
                _challengeLiveData.postValue(StateResponse.StateSuccess(challenge))
            }, { error ->
                _challengeLiveData.postValue(StateResponse.GenericError(error))
            })
    }
}