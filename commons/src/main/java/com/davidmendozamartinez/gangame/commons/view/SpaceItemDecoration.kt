package com.davidmendozamartinez.gangame.commons.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private var rightSpace: Int,
    private var bottomSpace: Int,
    private var leftSpace: Int,
    private var topSpace: Int,
    private var isLinearLayout: Boolean = false
) : RecyclerView.ItemDecoration() {
    private val firstPosition = 0

    constructor(space: Int, isLinearLayout: Boolean = false) :
            this(space, space, space, space, isLinearLayout)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)

        if (isLinearLayout && itemPosition != firstPosition) {
            setSpace(rightSpace, bottomSpace, leftSpace, 0, outRect)
        } else {
            setSpace(rightSpace, bottomSpace, leftSpace, topSpace, outRect)
        }
    }

    private fun setSpace(
        rightSpace: Int,
        bottomSpace: Int,
        leftSpace: Int,
        topSpace: Int,
        outRect: Rect
    ) {
        outRect.right = rightSpace
        outRect.bottom = bottomSpace
        outRect.left = leftSpace
        outRect.top = topSpace
    }
}