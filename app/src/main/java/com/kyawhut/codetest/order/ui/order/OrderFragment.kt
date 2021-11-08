package com.kyawhut.codetest.order.ui.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kyawhut.codetest.order.R
import com.kyawhut.codetest.order.adapter.OrderAdapter
import com.kyawhut.codetest.order.databinding.FragmentOrderBinding
import com.kyawhut.codetest.share.base.BaseFragmentWithVM
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@AndroidEntryPoint
class OrderFragment : BaseFragmentWithVM<FragmentOrderBinding, OrderViewModel>() {

    override val layoutID: Int
        get() = R.layout.fragment_order

    private val orderAdapter: OrderAdapter by lazy {
        OrderAdapter()
    }

    override val vm: OrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderAdapter.addAll((0..3).map { "$it" })

        vb.apply {
            orderAdapter = this@OrderFragment.orderAdapter
            executePendingBindings()
        }
    }
}
