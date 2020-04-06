package com.davidmendozamartinez.gangame.rated

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.davidmendozamartinez.commons.BaseListFragment
import com.davidmendozamartinez.commons.DataBindingRecyclerAdapter
import com.davidmendozamartinez.gangame.BR
import com.davidmendozamartinez.gangame.R
import com.davidmendozamartinez.gangame.TopGame

class TopRatedFragment : BaseListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (listAdapter as DataBindingRecyclerAdapter<TopGame>).setItems(getDummyTopGames())
    }

    override fun getAdapter(): RecyclerView.Adapter<*> =
        DataBindingRecyclerAdapter<TopGame>(BR.topGame, R.layout.item_top_game)

    private fun getDummyTopGames(): ArrayList<TopGame> =
        arrayListOf(
            TopGame(
                "Counter Strike",
                13407338,
                80,
                "Valve",
                9.99F,
                1,
                "https://cdn.steamstatic.com/steam/apps/10/capsule_184x69.jpg"
            )
        )
}