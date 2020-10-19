package com.santana.codewars.domain.usecase

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.santana.codewars.di.SchedulersParams
import com.santana.codewars.domain.model.ChallengeBO
import com.santana.codewars.domain.repository.CodewarsRepository
import com.santana.codewars.state.StateResponse
import com.santana.codewars.state.StateResponse.StateSuccess
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class ChallengeDataSource(
    private val repository: CodewarsRepository,
    private val compositeDisposable: CompositeDisposable,
    private val schedulers: SchedulersParams
) : PageKeyedDataSource<Int, ChallengeBO>() {

    var state: MutableLiveData<StateResponse<Any>> = MutableLiveData()
    private var retryCompletable: Completable? = null
    private lateinit var user: String

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ChallengeBO>
    ) {
        updateState(StateResponse.StateLoading())
        compositeDisposable.add(
            repository.getAuthoredChallenges(user, params.requestedLoadSize)
                .subscribe(
                    {
                        updateState(StateSuccess(Any()))
                        callback.onResult(it.data, null, 2)
                    },{
                        updateState(StateResponse.GenericError(it))
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ChallengeBO>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ChallengeBO>
    ) {
        updateState(StateResponse.StateLoading())
        compositeDisposable.add(
            repository.getAuthoredChallenges(user, params.key)
                .subscribe(
                    {
                        updateState(StateSuccess(Any()))
                        callback.onResult(it.data, params.key+1)
                    },{
                        updateState(StateResponse.GenericError(it))
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    private fun updateState(state: StateResponse<Any>) {
        this.state.postValue(state)
    }

    fun setUser(user:String){
        this.user = user
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(schedulers.ioScheduler)
                .observeOn(schedulers.mainScheduler)
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}