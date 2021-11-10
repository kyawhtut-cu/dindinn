package com.kyawhut.codetest.order.data.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @author kyawhtut
 * @date 11/9/21
 */
@Keep
data class CategoryResponse(
    @SerializedName("category_id")
    val categoryID: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("createdAt")
    val createdAt: String,
)
