package com.kyawhut.codetest.order.ui.order

import com.kyawhut.codetest.order.data.model.OrderModel
import com.kyawhut.codetest.order.data.model.OrderModel.Companion.toOrderModel
import com.kyawhut.codetest.order.data.network.API
import com.kyawhut.codetest.share.utils.Extension.backgroundThread
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class OrderRepositoryImpl @Inject constructor(private val api: API) : OrderRepository {

    override fun getOrder(): Single<List<OrderModel>> {
        return api.getOrderList().map {
            it.map {
                it.toOrderModel()
            }
        }.backgroundThread()
    }
}
