package com.kyawhut.codetest.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kyawhut.codetest.order.adapter.viewholder.OrderViewHolder
import com.kyawhut.codetest.order.databinding.ContentOrderViewBinding
import com.kyawhut.codetest.share.adapter.BaseAdapter
import com.kyawhut.codetest.share.adapter.BaseViewHolder

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class OrderAdapter : BaseAdapter<String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, String> {
        return OrderViewHolder(
            ContentOrderViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
