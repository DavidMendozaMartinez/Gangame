package com.davidmendozamartinez.gangame.sdk.retrofit

import retrofit2.Retrofit

interface GangameApiConfig {
    fun setupConfig(builder: Retrofit.Builder)
}