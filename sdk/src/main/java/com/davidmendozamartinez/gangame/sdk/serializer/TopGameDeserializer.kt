package com.davidmendozamartinez.gangame.sdk.serializer

import com.davidmendozamartinez.gangame.sdk.TopGame
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TopGameDeserializer : JsonDeserializer<TopGame> {

    companion object {
        const val BASE_IMG_URL = "https://cdn.steamstatic.com/steam/apps/%s/capsule_184x69.jpg"
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TopGame {
        val topGame = Gson().fromJson(json, TopGame::class.java)
        val appId = json.asJsonObject["appid"].asInt.toString()
        val thumb = String.format(BASE_IMG_URL, appId)

        topGame.thumb = thumb
        return topGame
    }
}