package com.davidmendozamartinez.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingRecyclerAdapter<MODEL>(
    private val itemVariableId: Int,
    private val itemLayoutResId: Int
) :
    RecyclerView.Adapter<DataBindingViewHolder<MODEL>>() {
    val items = mutableListOf<MODEL>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder<MODEL> {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            itemLayoutResId,
            parent,
            false
        )
        return DataBindingViewHolder(itemVariableId, binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<MODEL>, position: Int) {
        val item = items[position]
        holder.bindItem(item)
    }
}