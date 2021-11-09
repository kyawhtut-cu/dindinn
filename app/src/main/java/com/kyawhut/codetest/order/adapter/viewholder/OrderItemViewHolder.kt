package com.kyawhut.codetest.order.adapter.viewholder

import com.kyawhut.codetest.order.data.model.AddOnModel
import com.kyawhut.codetest.order.databinding.ItemOrderViewBinding
import com.kyawhut.codetest.share.adapter.BaseViewHolder

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class OrderItemViewHolder(
    vb: ItemOrderViewBinding,
) : BaseViewHolder<ItemOrderViewBinding, AddOnModel>(vb) {

    override fun bind() {
        data?.let {
            vb.apply {
                quantity = it.quantity
                name = it.title
                message = "Message - ${adapterPosition + 1}"
                executePendingBindings()
            }
        }
    }
}