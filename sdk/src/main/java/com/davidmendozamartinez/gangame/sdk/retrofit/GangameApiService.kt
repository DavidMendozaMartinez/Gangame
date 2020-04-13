package com.davidmendozamartinez.gangame.sdk.retrofit

import com.davidmendozamartinez.gangame.sdk.GangameClientConfig
import com.davidmendozamartinez.gangame.sdk.Routes
import com.davidmendozamartinez.gangame.sdk.TopGame
import com.davidmendozamartinez.gangame.sdk.serializer.ListTopGameDeserializer
import com.davidmendozamartinez.gangame.sdk.serializer.TopGameDeserializer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GangameApiService(apiConfig: GangameApiConfig = GangameClientConfig()) {

    val apiClient: RetrofitGangameApi

    init {
        val arrayListTopGameTokenType = object : TypeToken<ArrayList<TopGame>>() {}.type

        val gson = GsonBuilder()
            .registerTypeAdapter(TopGame::class.java, TopGameDeserializer())
            .registerTypeAdapter(arrayListTopGameTokenType, ListTopGameDeserializer())
            .create()

        val apiClientConfig =
            Retrofit.Builder()
                .baseUrl(Routes.BASE_URL_STEAM_SPY)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        apiConfig.setupConfig(apiClientConfig)
        apiClient = apiClientConfig.build().create(RetrofitGangameApi::class.java)
    }
}