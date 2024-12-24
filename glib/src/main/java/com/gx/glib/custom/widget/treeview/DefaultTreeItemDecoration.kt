package com.gx.glib.custom.widget.treeview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gx.glib.custom.widget.treeview.adapter.TreeViewAdapter

internal class DefaultTreeItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewHolder = parent.findContainingViewHolder(view)
        if (viewHolder is TreeViewAdapter.NodeHolder<*>) {
            outRect.set(
                space * viewHolder.nodeLevel,
                0,
                0,
                0
            )
        }
    }

}