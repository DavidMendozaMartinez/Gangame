package com.davidmendozamartinez.gangame.commons

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.view.*

abstract class BaseListFragment : BaseFragment() {

    lateinit var listAdapter: RecyclerView.Adapter<*>

    override fun getLayoutResId(): Int {
        return R.layout.fragment_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = getAdapter()

        view.list?.run {
            adapter = listAdapter

            val space: Int = resources.getDimension(R.dimen.normal_space).toInt()
            addItemDecoration(SpaceItemDecoration(space, true))
        }
    }

    abstract fun getAdapter(): RecyclerView.Adapter<*>
}