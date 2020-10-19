package com.santana.codewars.domain.usecase

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.santana.codewars.di.SchedulersParams
import com.santana.codewars.domain.model.ChallengeBO
import com.santana.codewars.domain.repository.CodewarsRepository
import io.reactivex.disposables.CompositeDisposable

class ChallengeDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val repository: CodewarsRepository,
    private val schedulers: SchedulersParams
) : DataSource.Factory<Int, ChallengeBO>(){

    val challengeDataSourceLiveData = MutableLiveData<ChallengeDataSource>()

    override fun create(): DataSource<Int, ChallengeBO> {
        val challDataSource = ChallengeDataSource(repository, compositeDisposable, schedulers)
        challengeDataSourceLiveData.postValue(challDataSource)
        return challDataSource
    }
}