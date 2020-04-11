package com.davidmendozamartinez.gangame.rated

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.davidmendozamartinez.commons.BaseListFragment
import com.davidmendozamartinez.commons.DataBindingRecyclerAdapter
import com.davidmendozamartinez.gangame.BR
import com.davidmendozamartinez.gangame.R
import com.davidmendozamartinez.gangame.Status
import com.davidmendozamartinez.gangame.TopGame
import com.google.android.material.snackbar.Snackbar

class TopRatedFragment : BaseListFragment() {
    private lateinit var viewModel: TopRatedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this).get() as TopRatedViewModel
        } ?: throw Exception("Invalid Activity")

        viewModel.games.observe(this, Observer { games ->
            when (games.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> games.data?.run { replaceItems(this) }
                Status.ERROR -> showError()
            }
        })
    }

    override fun getAdapter(): RecyclerView.Adapter<*> =
        DataBindingRecyclerAdapter<TopGame>(BR.topGame, R.layout.item_top_game)

    private fun replaceItems(list: ArrayList<TopGame>) {
        (listAdapter as DataBindingRecyclerAdapter<TopGame>).setItems(list.toMutableList())
    }

    private fun showError() {
        view?.let {
            Snackbar.make(
                view as View,
                R.string.error_top_rated_games_request,
                Snackbar.LENGTH_LONG
            )
                .setAction(R.string.label_retry) { _ -> viewModel.getTopRated() }
        }
    }
}