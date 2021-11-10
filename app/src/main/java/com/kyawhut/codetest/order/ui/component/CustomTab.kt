package com.kyawhut.codetest.order.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyawhut.codetest.order.databinding.ViewCustomTabBinding
import com.kyawhut.codetest.share.adapter.BaseAdapter
import com.kyawhut.codetest.share.adapter.BaseViewHolder
import com.kyawhut.codetest.share.utils.binding.RvBinding
import com.kyawhut.codetest.share.utils.binding.RvBinding.bindAdapter

/**
 * @author kyawhtut
 * @date 11/9/21
 */
class CustomTab @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerView(context, attrs, defStyleAttr) {

    companion object {
        @JvmStatic
        @BindingAdapter("tabList")
        fun CustomTab.addTabList(tabList: List<TabItem>?) {
            this.addAllTab(tabList ?: listOf())
        }

        fun tabItem(block: TabItem.Builder.() -> Unit): TabItem {
            return TabItem.Builder().also(block).build()
        }
    }

    private var _onTabClickedListener: ((TabItem) -> Unit)? = null
    private val tabAdapter: TabAdapter by lazy {
        TabAdapter { pos, item ->
            if (item.isSelected) return@TabAdapter
            val oldSelectedIndex = tabAdapter.indexOfFirst { it.isSelected }
            if (oldSelectedIndex != -1) {
                tabAdapter.update(
                    oldSelectedIndex,
                    tabAdapter.get(oldSelectedIndex)!!.apply {
                        isSelected = false
                    }
                )
            }
            tabAdapter.update(
                pos,
                item.apply {
                    isSelected = true
                }
            )
            _onTabClickedListener?.invoke(item)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        overScrollMode = OVER_SCROLL_NEVER

        bindAdapter(tabAdapter, RvBinding.LayoutType.HORIZONTAL_LAYOUT)
    }

    fun addAllTab(vararg tab: TabItem.Builder.() -> Unit) {
        tab.map {
            TabItem.Builder().also(it).build()
        }.also {
            addAllTab(it)
        }
    }

    fun addAllTab(tabList: List<TabItem>) {
        tabAdapter.addAll(tabList)
    }

    fun setOnTabItemClickedListener(onTabClickedListener: (TabItem) -> Unit) {
        this._onTabClickedListener = onTabClickedListener
    }

    class TabAdapter(
        itemClickListener: (Int, TabItem) -> Unit = { _, _ -> }
    ) : BaseAdapter<TabItem>(itemClickListener) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<*, TabItem> {
            return TabViewHolder(
                ViewCustomTabBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                itemClickListener,
            )
        }

    }

    class TabViewHolder(
        vb: ViewCustomTabBinding,
        itemClickListener: (Int, TabItem) -> Unit = { _, _ -> }
    ) : BaseViewHolder<ViewCustomTabBinding, TabItem>(vb, itemClickListener) {

        override fun bind() {
            data?.let {
                vb.apply {
                    tabName = it.title
                    root.isActivated = it.isSelected
                    executePendingBindings()
                }
            }
        }
    }

    data class TabItem(
        val id: Int,
        val title: String,
        var isSelected: Boolean = false
    ) {
        class Builder {
            var id: Int = 0
            var title: String = ""
            var isSelected: Boolean = false

            fun build(): TabItem {
                return TabItem(id, title, isSelected)
            }
        }
    }
}
