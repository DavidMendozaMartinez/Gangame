package com.davidmendozamartinez.gangame.deals

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.davidmendozamartinez.gangame.BR
import com.davidmendozamartinez.gangame.Deal
import com.davidmendozamartinez.gangame.R
import com.davidmendozamartinez.gangame.commons.BaseListFragment
import com.davidmendozamartinez.gangame.commons.databinding.DataBindingRecyclerAdapter
import com.davidmendozamartinez.gangame.data.Status
import com.google.android.material.snackbar.Snackbar

class DealsFragment : BaseListFragment() {
    private lateinit var viewModel: DealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this).get() as DealsViewModel
        } ?: throw Exception("Invalid Activity")

        viewModel.deals.observe(this, Observer { deals ->
            when (deals.status) {
                Status.SUCCESS -> deals.data?.run { replaceItems(this) }
                Status.ERROR -> showError()
            }
        })
    }

    override fun getAdapter(): RecyclerView.Adapter<*> =
        DataBindingRecyclerAdapter<Deal>(
            BR.deal,
            R.layout.item_deal
        )

    private fun replaceItems(list: ArrayList<Deal>) {
        (listAdapter as DataBindingRecyclerAdapter<Deal>).setItems(list.toMutableList())
    }

    private fun showError() {
        view?.let {
            Snackbar.make(view as View, R.string.error_deals_request, Snackbar.LENGTH_LONG)
                .setAction(R.string.label_retry) { _ -> viewModel.getDeals() }
        }
    }
}