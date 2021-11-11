package com.kyawhut.codetest.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kyawhut.codetest.order.adapter.viewholder.OrderViewHolder
import com.kyawhut.codetest.order.data.model.OrderModel
import com.kyawhut.codetest.order.databinding.ContentOrderViewBinding
import com.kyawhut.codetest.share.adapter.BaseAdapter
import com.kyawhut.codetest.share.adapter.BaseViewHolder

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class OrderAdapter(
    private val onAlert: () -> Unit,
    private val onExpired: () -> Unit,
    private val onClickedAccept: (Int) -> Unit,
) : BaseAdapter<OrderModel>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*, OrderModel> {
        return OrderViewHolder(
            ContentOrderViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onAlert, onExpired, onClickedAccept
        )
    }
}
