package com.kyawhut.codetest.order.data.network

import com.kyawhut.codetest.order.data.network.response.OrderResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author kyawhtut
 * @date 11/8/21
 */
interface API {

    @GET("v1/order")
    fun getOrderList(): Single<List<OrderResponse>>
}
