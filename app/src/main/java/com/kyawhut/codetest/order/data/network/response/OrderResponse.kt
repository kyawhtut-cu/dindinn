package com.kyawhut.codetest.order.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@Keep
data class OrderResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("add_on")
    val addOn: List<AddOnResponse>,
    @SerializedName("expire_at")
    val expireAt: String,
    @SerializedName("alert_at")
    val alertAt: String,
    @SerializedName("created_at")
    val createdAt: String,
)
