package com.davidmendozamartinez.gangame.sdk.retrofit

import com.davidmendozamartinez.gangame.sdk.Deal
import com.davidmendozamartinez.gangame.sdk.Routes
import com.davidmendozamartinez.gangame.sdk.TopGame
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitGangameApi {

    @GET(Routes.GET_DEALS)
    fun getDealsObservable(): Observable<ArrayList<Deal>>

    @GET(Routes.GET_TOP_100_GAMES)
    fun getTopRatedGamesObservable(): Observable<ArrayList<TopGame>>

    @GET(Routes.GET_MOST_OWNED_GAMES)
    fun getMostOwnedGamesObservable(): Observable<ArrayList<TopGame>>
}