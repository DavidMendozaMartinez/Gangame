package com.davidmendozamartinez.gangame.sdk

import com.davidmendozamartinez.gangame.sdk.serializer.TopGameDeserializer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GangameApiService(apiConfig: GangameApiConfig = GangameClientConfig()) {

    private val apiClient: RetrofitGangameApi

    init {
        val gson = GsonBuilder()
            .registerTypeAdapter(TopGame::class.java, TopGameDeserializer())
            .create()

        val apiClientConfig =
            Retrofit.Builder()
                .baseUrl(Routes.BASE_URL_STEAM_SPY)
                .addConverterFactory(GsonConverterFactory.create(gson))

        apiConfig.setupConfig(apiClientConfig)
        apiClient = apiClientConfig.build().create(RetrofitGangameApi::class.java)
    }
}