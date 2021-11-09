package com.kyawhut.codetest.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kyawhut.codetest.order.adapter.viewholder.OrderItemViewHolder
import com.kyawhut.codetest.order.data.model.AddOnModel
import com.kyawhut.codetest.order.databinding.ItemOrderViewBinding
import com.kyawhut.codetest.share.adapter.BaseAdapter
import com.kyawhut.codetest.share.adapter.BaseViewHolder

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class OrderItemAdapter : BaseAdapter<AddOnModel>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*, AddOnModel> {
        return OrderItemViewHolder(
            ItemOrderViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
