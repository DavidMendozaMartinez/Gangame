package com.davidmendozamartinez.gangame.data.mapper

import com.davidmendozamartinez.gangame.TopGame

object TopGameMapper {

    fun fromSdk(topGame: com.davidmendozamartinez.gangame.sdk.TopGame, position: Int): TopGame =
        TopGame(
            topGame.title,
            topGame.owners,
            (topGame.positive.toFloat() / (topGame.positive + topGame.negative) * 100).toInt(),
            topGame.publisher,
            topGame.price / 100F,
            position,
            topGame.thumb
        )
}