package com.kyawhut.codetest.order.ui.order

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kyawhut.codetest.order.BR
import com.kyawhut.codetest.order.R
import com.kyawhut.codetest.order.adapter.OrderAdapter
import com.kyawhut.codetest.order.data.model.OrderModel
import com.kyawhut.codetest.order.databinding.FragmentOrderBinding
import com.kyawhut.codetest.share.base.BaseFragmentWithVM
import com.kyawhut.codetest.share.network.utils.NetworkResponse
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

    override val onClickListener: Int
        get() = BR.onClickListener

    override val vm: OrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getOrder(::onOrderNetworkState)

        vb.apply {
            orderAdapter = this@OrderFragment.orderAdapter
            executePendingBindings()
        }
    }

    private fun onOrderNetworkState(state: NetworkResponse<List<OrderModel>>) {
        vb.apply {
            isLoading = state.isLoading
            executePendingBindings()
        }

        if (state.isSuccess) {
            orderAdapter.addAll(state.data ?: listOf())
        } else if (state.isError && state.error != null) {
            state.error?.let { error ->
                Toast.makeText(
                    requireContext(),
                    error.message.takeIf { it.isNotEmpty() } ?: getString(error.resId),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.action_order_to_ingredient -> {
                findNavController().navigate(v.id)
            }
        }
    }
}
