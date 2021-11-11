package com.kyawhut.codetest.order.adapter.viewholder

import com.kyawhut.codetest.order.adapter.OrderItemAdapter
import com.kyawhut.codetest.order.data.model.AddOnModel
import com.kyawhut.codetest.order.data.model.OrderModel
import com.kyawhut.codetest.order.databinding.ContentOrderViewBinding
import com.kyawhut.codetest.share.adapter.BaseViewHolder
import java.util.*

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class OrderViewHolder(
    vb: ContentOrderViewBinding,
    private val onAlert: () -> Unit,
    private val onExpired: () -> Unit,
    private val onClickedAccept: (Int) -> Unit,
) : BaseViewHolder<ContentOrderViewBinding, OrderModel>(vb) {

    private val currentDate: Date = Date()

    override fun bind() {
        data?.let {
            vb.apply {
                viewOrderHeader.alertAt = Date(
                    currentDate.time + (1 * 60 * 1000)
                ) // this is a mock to test alert
                viewOrderHeader.expireAt = Date(
                    currentDate.time + (2 * 60 * 1000)
                ) // this is a mock to test expire
                viewOrderHeader.setOnAlertListener(onAlert)
                viewOrderHeader.setOnExpiredListener {
                    isExpired = true
                    onExpired()
                }
                viewOrderHeader.startTimer()

                orderNumber = it.id.toInt()
                orderTime = it.orderAt
                orderItemAdapter = OrderItemAdapter().apply {
                    addAll(it.addOn.toMutableList().apply {
                        add(0, AddOnModel(it.id.toInt(), it.title, it.quantity))
                    })
                }

                vb.actionAcceptOrOk.setOnClickListener {
                    viewOrderHeader.stopTimer()
                    onClickedAccept(adapterPosition)
                }
            }
        }
    }
}
