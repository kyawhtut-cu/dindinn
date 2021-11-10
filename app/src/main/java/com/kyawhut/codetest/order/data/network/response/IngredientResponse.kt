package com.kyawhut.codetest.order.data.network.response

import com.google.gson.annotations.SerializedName

/**
 * @author kyawhtut
 * @date 11/9/21
 */
data class IngredientResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("available_count")
    val availableCount: Int,
    @SerializedName("category_id")
    val categoryID: Int,
    @SerializedName("createdAt")
    val createdAt: String,
)
