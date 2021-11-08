package com.kyawhut.codetest.order.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.kyawhut.codetest.order.R
import com.kyawhut.codetest.order.databinding.ViewTimeDotBinding
import com.kyawhut.codetest.share.adapter.BaseAdapter
import com.kyawhut.codetest.share.adapter.BaseViewHolder
import com.kyawhut.codetest.share.utils.binding.RvBinding
import com.kyawhut.codetest.share.utils.binding.RvBinding.bindAdapter

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class ViewTimeDot @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerView(context, attrs, defStyleAttr) {

    private val dotList: MutableList<Int> = mutableListOf()
    private val dotAdapter: DotAdapter by lazy {
        DotAdapter()
    }

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.ViewTimeDot,
            defStyleAttr,
            0
        )
        try {
            val dotCount = a.getInteger(R.styleable.ViewTimeDot_dotCount, 0)
            val activeIndex = a.getInteger(R.styleable.ViewTimeDot_activeIndex, dotCount) - 1
            repeat((0 until dotCount).count()) { index ->
                dotList.add(1.takeIf { index == activeIndex } ?: 0)
            }
            dotAdapter.addAll(dotList)
        } finally {
            a.recycle()
        }
    }

    fun showActiveIndex(index: Int) {
        dotList.forEach {
            dotList[it] = 0
        }
        dotList[index] = 1
        dotAdapter.addAll(dotList)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (dotList.isEmpty()) {
            isVisible = false
            return
        }

        this.bindAdapter(dotAdapter, RvBinding.LayoutType.HORIZONTAL_LAYOUT)

    }

    class DotAdapter : BaseAdapter<Int>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, Int> {
            return DotViewHolder(
                ViewTimeDotBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    class DotViewHolder(vb: ViewTimeDotBinding) : BaseViewHolder<ViewTimeDotBinding, Int>(vb) {
        override fun bind() {
            vb.root.isActivated = data == 1
        }
    }
}
