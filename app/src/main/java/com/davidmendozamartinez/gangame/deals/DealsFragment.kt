package com.davidmendozamartinez.gangame.deals

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.davidmendozamartinez.commons.BaseListFragment
import com.davidmendozamartinez.commons.DataBindingRecyclerAdapter
import com.davidmendozamartinez.gangame.BR
import com.davidmendozamartinez.gangame.Deal
import com.davidmendozamartinez.gangame.R

class DealsFragment : BaseListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (listAdapter as DataBindingRecyclerAdapter<Deal>).setItems(getDummyDeals())
    }

    override fun getAdapter(): RecyclerView.Adapter<*> =
        DataBindingRecyclerAdapter<Deal>(BR.deal, R.layout.item_deal)


    private fun getDummyDeals(): ArrayList<Deal> =
        arrayListOf(
            Deal(
                "Counter Strike",
                0.99F,
                9.99F,
                80,
                80,
                "https://cdn.steamstatic.com/steam/apps/10/capsule_184x69.jpg"
            )
        )
}