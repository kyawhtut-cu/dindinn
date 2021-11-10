package com.kyawhut.codetest.order.data.network

import com.kyawhut.codetest.order.data.network.response.CategoryResponse
import com.kyawhut.codetest.order.data.network.response.IngredientResponse
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

    @GET("v1/category")
    fun getCategoryList(): Single<List<CategoryResponse>>

    @GET("v1/ingredient")
    fun getIngredientList(): Single<List<IngredientResponse>>
}
