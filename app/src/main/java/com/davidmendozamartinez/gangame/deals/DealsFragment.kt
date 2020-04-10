package com.davidmendozamartinez.gangame.deals

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.davidmendozamartinez.commons.BaseListFragment
import com.davidmendozamartinez.commons.DataBindingRecyclerAdapter
import com.davidmendozamartinez.gangame.BR
import com.davidmendozamartinez.gangame.Deal
import com.davidmendozamartinez.gangame.R
import com.davidmendozamartinez.gangame.data.GangameDataSource
import com.google.android.material.snackbar.Snackbar


class DealsFragment : BaseListFragment() {

    override fun onResume() {
        getDeals()
        super.onResume()
    }

    override fun getAdapter(): RecyclerView.Adapter<*> =
        DataBindingRecyclerAdapter<Deal>(BR.deal, R.layout.item_deal)

    private fun getDeals() {
        GangameDataSource
            .getDeals()
            .subscribe({ list ->
                replaceItems(list)
            }, { error ->
                showError(error)
            })
    }

    private fun replaceItems(list: List<Deal>) {
        (listAdapter as DataBindingRecyclerAdapter<Deal>).setItems(list.toMutableList())
    }

    private fun showError(error: Throwable) {
        error.printStackTrace()

        view?.let {
            Snackbar.make(view as View, R.string.error_deals_request, Snackbar.LENGTH_LONG)
                .setAction(R.string.label_retry) { _ -> getDeals() }
        }
    }
}