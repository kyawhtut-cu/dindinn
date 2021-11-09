package com.kyawhut.codetest.order.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@Keep
data class AddOnResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("quantity")
    val quantity: Int,
)
