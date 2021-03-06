package com.davidmendozamartinez.gangame.sdk

import com.davidmendozamartinez.gangame.sdk.retrofit.GangameApiService
import com.davidmendozamartinez.gangame.sdk.serializer.TopGameDeserializer
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.junit.Assert
import org.junit.Test

class RequestTest {

    @Test
    fun dealRequestSuccess() {
        val apiService = GangameApiService()
        val dealsObserver = apiService.apiClient.getDealsObservable().test()
        val deals = dealsObserver.values().first()

        val jsonResponse: JsonArray =
            JsonParser.parseString(MockResponses.DEALS_RESPONSE).asJsonArray

        dealsObserver.assertComplete()
        dealsObserver.dispose()

        deals?.let {
            Assert.assertEquals(deals.size, jsonResponse.size())

            deals.zip(jsonResponse).forEach { (deal, jsonDeal) ->
                with(jsonDeal.asJsonObject) {
                    Assert.assertEquals(deal.title, this["title"].asString)
                    Assert.assertEquals(deal.salePrice, this["salePrice"].asFloat)
                    Assert.assertEquals(deal.normalPrice, this["normalPrice"].asFloat)
                    Assert.assertEquals(deal.metacriticScore, this["metacriticScore"].asInt)
                    Assert.assertEquals(deal.steamRating, this["steamRatingPercent"].asInt)
                    Assert.assertEquals(deal.thumb, this["thumb"].asString)
                }
            }
        }

    }

    @Test
    fun topRatedRequestSuccess() {
        val apiService = GangameApiService()
        val topGamesObserver = apiService.apiClient.getTopRatedGamesObservable().test()
        val topGames = topGamesObserver.values().first()

        val jsonResponse: List<JsonObject> =
            JsonParser.parseString(MockResponses.TOP_GAMES_RESPONSE)
                .asJsonObject
                .entrySet()
                .map { (_, json) ->
                    json.asJsonObject
                }

        topGamesObserver.assertComplete()
        topGamesObserver.dispose()

        topGames?.let {
            Assert.assertEquals(topGames.size, jsonResponse.size)

            topGames.zip(jsonResponse).forEach { (topGame, jsonTopGame) ->
                with(jsonTopGame.asJsonObject) {
                    Assert.assertEquals(topGame.title, this["name"].asString)
                    Assert.assertEquals(topGame.publisher, this["publisher"].asString)
                    Assert.assertEquals(topGame.positive, this["positive"].asInt)
                    Assert.assertEquals(topGame.negative, this["negative"].asInt)
                    Assert.assertEquals(topGame.owners, this["owners"].asString)
                    Assert.assertEquals(topGame.price, this["price"].asInt)
                    Assert.assertEquals(
                        topGame.thumb,
                        String.format(TopGameDeserializer.BASE_IMG_URL, this["appid"].asString)
                    )
                }
            }
        }
    }
}