package com.davidmendozamartinez.gangame.deals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davidmendozamartinez.gangame.Deal
import com.davidmendozamartinez.gangame.Resource
import com.davidmendozamartinez.gangame.data.GangameDataSource
import io.reactivex.disposables.CompositeDisposable

class DealsViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _deals = MutableLiveData<Resource<ArrayList<Deal>>>()
    val deals: LiveData<Resource<ArrayList<Deal>>> get() = _deals

    init {
        getDeals()
    }

    fun getDeals() {
        val disposable = GangameDataSource
            .getDeals()
            .doOnSubscribe {
                _deals.value = Resource.loading()
            }
            .subscribe({ list ->
                _deals.value = Resource.success(list)
            }, { error ->
                error.printStackTrace()
                _deals.value = Resource.error(error.message)
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}