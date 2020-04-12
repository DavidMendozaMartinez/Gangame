package com.davidmendozamartinez.gangame.commons

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<MODEL>(
    private val itemVariableId: Int,
    private val binding: ViewDataBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: MODEL) {
        binding.setVariable(itemVariableId, item)
        binding.executePendingBindings()
    }
}