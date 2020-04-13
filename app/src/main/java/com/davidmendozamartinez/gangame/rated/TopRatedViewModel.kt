package com.davidmendozamartinez.gangame.rated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidmendozamartinez.gangame.TopGame
import com.davidmendozamartinez.gangame.data.GangameDataSource
import com.davidmendozamartinez.gangame.data.Resource
import io.reactivex.disposables.CompositeDisposable

class TopRatedViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _games = MutableLiveData<Resource<ArrayList<TopGame>>>()
    val games: LiveData<Resource<ArrayList<TopGame>>> get() = _games

    init {
        getTopRated()
    }

    fun getTopRated() {
        val disposable = GangameDataSource
            .getTopRated()
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