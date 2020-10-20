package com.santana.codewars.ui.userchallenges.authored

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santana.codewars.domain.model.ChallengesAuthoredBO
import com.santana.codewars.domain.model.UserBO
import com.santana.codewars.domain.usecase.FetchChallengesAuthoredUseCase
import com.santana.codewars.state.StateResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ChallengesAuthoredViewModel @ViewModelInject constructor(
    private val challengesAuthoredUseCase: FetchChallengesAuthoredUseCase,
    private val scheduler: Scheduler
) : ViewModel() {

    private val _challengesLiveData = MutableLiveData<StateResponse<List<ChallengesAuthoredBO>>>()
    val challengesLiveData get(): LiveData<StateResponse<List<ChallengesAuthoredBO>>> = _challengesLiveData
    private val disposables = CompositeDisposable()
    private lateinit var user: UserBO

    fun selectUser(user: UserBO) {
        this.user = user
    }

    fun fetchChallengesCompleted(page: Int) {
        val disposable = challengesAuthoredUseCase
            .execute(FetchChallengesAuthoredUseCase.Params(user.username, page))
            .subscribeOn(scheduler)
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
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared ();
    }
}