package com.dariobrux.pokemon.ui.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 *
 * Created by Dario Bruzzese on 22/10/2020.
 *
 * This class is the ItemDecoration useful for the RecyclerView in grid visualization.
 */
class TypeSpaceItemDecoration(private val space: Int) : ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let {
            val position = parent.getChildAdapterPosition(view)

            if (position % 2 != 0) {
                outRect.left = space / 2
                outRect.right = space
            } else {
                outRect.left = space
                outRect.right = space / 2
            }
        }
    }
}