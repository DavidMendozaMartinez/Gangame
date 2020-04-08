package com.davidmendozamartinez.gangame.sdk

import com.google.gson.annotations.SerializedName

data class Deal(
    var title: String,
    var salePrice: Float,
    var normalPrice: Float,
    var metacriticScore: Int,
    @SerializedName("steamRatingPercent") var steamRating: Int,
    var thumb: String
)

data class TopGame(
    @SerializedName("name") var title: String,
    var publisher: String,
    var positive: Int,
    var negative: Int,
    var owners: String,
    var price: Int,
    var thumb: String
)