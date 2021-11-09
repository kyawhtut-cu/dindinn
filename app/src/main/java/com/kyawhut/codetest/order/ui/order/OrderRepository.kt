package com.kyawhut.codetest.order.ui.order

import com.kyawhut.codetest.order.data.model.OrderModel
import io.reactivex.Single

/**
 * @author kyawhtut
 * @date 11/8/21
 */
interface OrderRepository {

    fun getOrder(): Single<List<OrderModel>>
}
