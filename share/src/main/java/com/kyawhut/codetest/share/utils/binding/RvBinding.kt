package com.kyawhut.codetest.share.utils.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kyawhut.codetest.share.adapter.BaseAdapter

/**
 * @author kyawhtut
 * @date 11/8/21
 */
object RvBinding {

    enum class LayoutType {
        HORIZONTAL_LAYOUT, VERTICAL_LAYOUT, GRIDLAYOUT
    }

    @JvmStatic
    @BindingAdapter("adapter", "layoutManger", "itemCount", requireAll = false)
    fun RecyclerView.bindAdapter(
        adapter: BaseAdapter<*>?,
        layoutType: LayoutType?,
        itemCount: Int? = 0
    ) {
        if (adapter == null) return
        this.apply {
            this.adapter = adapter
            layoutManager = when (layoutType) {
                LayoutType.HORIZONTAL_LAYOUT -> LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                LayoutType.VERTICAL_LAYOUT -> LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                LayoutType.GRIDLAYOUT -> GridLayoutManager(context, itemCount ?: 1)
                else -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}
