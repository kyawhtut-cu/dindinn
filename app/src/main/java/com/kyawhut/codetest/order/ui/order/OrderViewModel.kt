package com.kyawhut.codetest.order.ui.order

import com.kyawhut.codetest.order.data.model.OrderModel
import com.kyawhut.codetest.share.base.BaseViewModel
import com.kyawhut.codetest.share.network.utils.NetworkResponse
import com.kyawhut.codetest.share.network.utils.NetworkResponse.Companion.error
import com.kyawhut.codetest.share.network.utils.NetworkResponse.Companion.loading
import com.kyawhut.codetest.share.network.utils.NetworkResponse.Companion.success
import com.kyawhut.codetest.share.network.utils.error
import com.kyawhut.codetest.share.utils.Extension.bothThread
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repo: OrderRepository
) : BaseViewModel() {

    fun getOrder(callback: (NetworkResponse<List<OrderModel>>) -> Unit) {
        callback(loading())
        repo.getOrder().bothThread().subscribe(
            {
                callback(success(it))
            },
            {
                callback(error(it.error))
            },
        ).addTo(disposable)
    }
}
