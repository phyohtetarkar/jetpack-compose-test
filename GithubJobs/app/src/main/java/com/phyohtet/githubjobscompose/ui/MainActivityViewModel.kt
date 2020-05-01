package com.phyohtet.githubjobscompose.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.phyohtet.githubjobscompose.ServiceLocator
import com.phyohtet.githubjobscompose.model.ApiResource
import com.phyohtet.githubjobscompose.model.NetworkState
import com.phyohtet.githubjobscompose.model.dto.JobPositionDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val api = ServiceLocator.getInstance(application).getGitHubJobApi()

    private val disposable = CompositeDisposable();

    val positions = MutableLiveData<ApiResource<List<JobPositionDTO>>>()

    fun find(page: Int) {
        disposable.add(api.findPositions("", "", page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                positions.value = ApiResource(null, NetworkState.LOADING)
            }
            .subscribe { positions.value = ApiResource(it, NetworkState.LOADED) })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}