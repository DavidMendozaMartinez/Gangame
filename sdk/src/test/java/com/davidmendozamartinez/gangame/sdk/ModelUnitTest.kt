package com.davidmendozamartinez.gangame.sdk

import com.davidmendozamartinez.gangame.sdk.serializer.TopGameDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Assert
import org.junit.Test

class ModelUnitTest {

    private val jsonDeal = "{" +
            "\"internalName\": \"IRONDANGER\"," +
            "\"title\": \"Iron Danger\"," +
            "\"metacriticLink\": \"/game/pc/iron-danger\"," +
            "\"dealID\": \"rBZg6Dk5gvbfiWRNnxkdPqXxOdUcLn%2BiD5vyJlvmqOI%3D\"," +
            "\"storeID\": \"23\"," +
            "\"gameID\": \"212422\"," +
            "\"salePrice\": \"22.69\"," +
            "\"normalPrice\": \"34.99\"," +
            "\"isOnSale\": \"1\"," +
            "\"savings\": \"35.152901\"," +
            "\"metacriticScore\": \"75\"," +
            "\"steamRatingText\": \"Very Positive\"," +
            "\"steamRatingPercent\": \"82\"," +
            "\"steamRatingCount\": \"129\"," +
            "\"steamAppID\": \"899310\"," +
            "\"releaseDate\": 1585094400," +
            "\"lastChange\": 1585311835," +
            "\"dealRating\": \"10.0\"," +
            "\"thumb\": \"https://steamcdn-a.akamaihd.net/steam/apps/899310/capsule_sm_120.jpg?t=1585825568\"" +
            "}"

    private val jsonTopGame = "{" +
            "\"appid\": 570," +
            "\"name\": \"Dota 2\"," +
            "\"developer\": \"Valve\"," +
            "\"publisher\": \"Valve\"," +
            "\"score_rank\": \"\"," +
            "\"positive\": 1073235," +
            "\"negative\": 188264," +
            "\"userscore\": 0," +
            "\"owners\": \"100,000,000 .. 200,000,000\"," +
            "\"average_forever\": 27431," +
            "\"average_2weeks\": 1604," +
            "\"median_forever\": 917," +
            "\"median_2weeks\": 772," +
            "\"price\": \"0\"," +
            "\"initialprice\": \"0\"," +
            "\"discount\": \"0\"" +
            "}"

    @Test
    fun dealParsingTest() {
        val deal = Gson().fromJson(jsonDeal, Deal::class.java)

        Assert.assertEquals(deal.title, "Iron Danger")
        Assert.assertEquals(deal.salePrice, 22.69F)
        Assert.assertEquals(deal.normalPrice, 34.99F)
        Assert.assertEquals(deal.metacriticScore, 75)
        Assert.assertEquals(deal.steamRating, 82)
        Assert.assertEquals(
            deal.thumb,
            "https://steamcdn-a.akamaihd.net/steam/apps/899310/capsule_sm_120.jpg?t=1585825568"
        )
    }

    @Test
    fun topGameParsingTest() {
        val gson = GsonBuilder()
            .registerTypeAdapter(TopGame::class.java, TopGameDeserializer())
            .create()

        val topGame = gson.fromJson(jsonTopGame, TopGame::class.java)

        Assert.assertEquals(topGame.title, "Dota 2")
        Assert.assertEquals(topGame.publisher, "Valve")
        Assert.assertEquals(topGame.positive, 1073235)
        Assert.assertEquals(topGame.negative, 188264)
        Assert.assertEquals(topGame.owners, "100,000,000 .. 200,000,000")
        Assert.assertEquals(topGame.price, 0)
        Assert.assertEquals(
            topGame.thumb,
            "https://cdn.steamstatic.com/steam/apps/570/capsule_184x69.jpg"
        )
    }
}