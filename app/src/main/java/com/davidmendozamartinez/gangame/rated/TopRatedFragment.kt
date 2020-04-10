package com.davidmendozamartinez.gangame.rated

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.davidmendozamartinez.commons.BaseListFragment
import com.davidmendozamartinez.commons.DataBindingRecyclerAdapter
import com.davidmendozamartinez.gangame.BR
import com.davidmendozamartinez.gangame.R
import com.davidmendozamartinez.gangame.TopGame
import com.davidmendozamartinez.gangame.data.GangameDataSource
import com.google.android.material.snackbar.Snackbar

class TopRatedFragment : BaseListFragment() {

    override fun onResume() {
        super.onResume()
        getTopRated()
    }

    override fun getAdapter(): RecyclerView.Adapter<*> =
        DataBindingRecyclerAdapter<TopGame>(BR.topGame, R.layout.item_top_game)

    private fun getTopRated() {
        GangameDataSource
            .getTopRated()
            .subscribe({ list ->
                replaceItems(list)
            }, { error ->
                showError(error)
            })
    }

    private fun replaceItems(list: List<TopGame>) {
        (listAdapter as DataBindingRecyclerAdapter<TopGame>).setItems(list.toMutableList())
    }

    private fun showError(error: Throwable) {
        error.printStackTrace()

        view?.let {
            Snackbar.make(
                view as View,
                R.string.error_top_rated_games_request,
                Snackbar.LENGTH_LONG
            )
                .setAction(R.string.label_retry) { _ -> getTopRated() }
                .show()
        }
    }
}