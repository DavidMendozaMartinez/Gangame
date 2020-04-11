package com.davidmendozamartinez.gangame.owned

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidmendozamartinez.gangame.Resource
import com.davidmendozamartinez.gangame.TopGame
import com.davidmendozamartinez.gangame.data.GangameDataSource
import io.reactivex.disposables.CompositeDisposable

class MostOwnedViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _games = MutableLiveData<Resource<ArrayList<TopGame>>>()
    val games: LiveData<Resource<ArrayList<TopGame>>> get() = _games

    init {
        getMostOwned()
    }

    fun getMostOwned() {
        val disposable = GangameDataSource
            .getMostOwned()
            .doOnSubscribe {
                _games.value = Resource.loading()
            }
            .subscribe({ list ->
                _games.value = Resource.success(list)
            }, { error ->
                error.printStackTrace()
                _games.value = Resource.error(error.message)
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}