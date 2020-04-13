package com.davidmendozamartinez.gangame.data

import com.davidmendozamartinez.gangame.Deal
import com.davidmendozamartinez.gangame.TopGame
import com.davidmendozamartinez.gangame.data.mapper.DealMapper
import com.davidmendozamartinez.gangame.data.mapper.TopGameMapper
import com.davidmendozamartinez.gangame.sdk.retrofit.GangameApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object GangameDataSource {
    private val apiService =
        GangameApiService()

    fun getDeals(): Observable<ArrayList<Deal>> =
        apiService.apiClient
            .getDealsObservable()
            .map { listDeal ->
                val deals = listDeal.map { deal -> DealMapper.fromSdk(deal) }
                val arrayListDeal = arrayListOf<Deal>()
                arrayListDeal.addAll(deals)
                arrayListDeal
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getTopRated(): Observable<ArrayList<TopGame>> =
        apiService.apiClient
            .getTopRatedGamesObservable()
            .map { listTopGame ->
                val topGames = listTopGame.mapIndexed { index, topGame ->
                    TopGameMapper.fromSdk(
                        topGame,
                        (index + 1)
                    )
                }
                val arrayListTopGame = arrayListOf<TopGame>()
                arrayListTopGame.addAll(topGames)
                arrayListTopGame
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getMostOwned(): Observable<ArrayList<TopGame>> =
        apiService.apiClient
            .getMostOwnedGamesObservable()
            .map { listTopGame ->
                val topGames = listTopGame.mapIndexed { index, topGame ->
                    TopGameMapper.fromSdk(
                        topGame,
                        (index + 1)
                    )
                }
                val arrayListTopGame = arrayListOf<TopGame>()
                arrayListTopGame.addAll(topGames)
                arrayListTopGame
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}