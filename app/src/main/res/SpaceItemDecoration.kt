package silmood.com.commons.view

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpaceItemDecoration(var rightSpace: Int,
                          var bottomSpace: Int,
                          var leftSpace: Int,
                          var topSpace: Int,
                          var isLinearLayout: Boolean = false) : RecyclerView.ItemDecoration() {
    val FIRST_POSITION = 0

    constructor(space: Int, isLinearLayout: Boolean = false) :
            this(space, space, space, space, isLinearLayout)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition = parent.getChildAdapterPosition(view)

        if (isLinearLayout && itemPosition != FIRST_POSITION) {
            setSpace(rightSpace, bottomSpace, leftSpace, 0, outRect)
        } else {
            setSpace(rightSpace, bottomSpace, leftSpace, topSpace, outRect)
        }
    }

    private fun setSpace(rightSpace: Int, bottomSpace: Int, leftSpace: Int, topSpace: Int, outRect: Rect) {
        outRect.right = rightSpace
        outRect.bottom = bottomSpace
        outRect.left = leftSpace
        outRect.top = topSpace
    }
}