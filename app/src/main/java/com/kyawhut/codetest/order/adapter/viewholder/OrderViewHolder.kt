package com.kyawhut.codetest.order.adapter.viewholder

import com.kyawhut.codetest.order.adapter.OrderItemAdapter
import com.kyawhut.codetest.order.data.model.AddOnModel
import com.kyawhut.codetest.order.data.model.OrderModel
import com.kyawhut.codetest.order.databinding.ContentOrderViewBinding
import com.kyawhut.codetest.share.adapter.BaseViewHolder

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class OrderViewHolder(
    vb: ContentOrderViewBinding
) : BaseViewHolder<ContentOrderViewBinding, OrderModel>(vb) {

    override fun bind() {
        data?.let {
            vb.apply {
                orderNumber = it.id.toInt()
                orderTime = it.orderAt
                orderItemAdapter = OrderItemAdapter().apply {
                    addAll(it.addOn.toMutableList().apply {
                        add(0, AddOnModel(it.id.toInt(), it.title, it.quantity))
                    })
                }
            }
        }
    }
}
