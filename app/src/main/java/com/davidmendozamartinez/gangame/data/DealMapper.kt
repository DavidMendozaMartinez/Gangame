package com.davidmendozamartinez.gangame.data

import com.davidmendozamartinez.gangame.Deal

object DealMapper {

    fun fromSdk(deal: com.davidmendozamartinez.gangame.sdk.Deal): Deal =
        Deal(
            deal.title,
            deal.salePrice,
            deal.normalPrice,
            deal.metacriticScore,
            deal.steamRating,
            deal.thumb
        )
}