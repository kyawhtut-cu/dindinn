package com.kyawhut.codetest.order.data.model

import com.kyawhut.codetest.order.data.model.AddOnModel.Companion.toAddOnModel
import com.kyawhut.codetest.order.data.network.response.OrderResponse
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author kyawhtut
 * @date 11/8/21
 */
data class OrderModel(
    val id: String,
    val title: String,
    val quantity: Int,
    val addOn: List<AddOnModel>,
    val expireAt: Date,
    val alertAt: Date,
    val createdAt: Date,
) {
    companion object {
        private const val UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val TIME_FORMAT = "hh:mma"

        fun OrderResponse.toOrderModel(): OrderModel {
            return OrderModel(
                id,
                title,
                quantity,
                addOn.map { it.toAddOnModel() },
                SimpleDateFormat(UTC_FORMAT, Locale.ENGLISH).parse(expireAt) ?: Date(),
                SimpleDateFormat(UTC_FORMAT, Locale.ENGLISH).parse(alertAt) ?: Date(),
                SimpleDateFormat(UTC_FORMAT, Locale.ENGLISH).parse(createdAt) ?: Date(),
            )
        }
    }

    val orderAt: String
        get() = SimpleDateFormat(
            TIME_FORMAT,
            Locale.ENGLISH
        ).format(Date()) // this is a mock to test created
}
