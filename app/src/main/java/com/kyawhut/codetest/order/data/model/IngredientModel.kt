package com.kyawhut.codetest.order.data.model

import com.kyawhut.codetest.order.data.network.response.IngredientResponse

/**
 * @author kyawhtut
 * @date 11/9/21
 */
data class IngredientModel(
    val id: String,
    val name: String,
    val image: String,
    val availableCount: Int,
    val categoryID: Int,
    val createdAt: String,
) {
    companion object {
        fun IngredientResponse.toIngredientModel(): IngredientModel {
            return IngredientModel(
                id, name, image, availableCount, categoryID, createdAt,
            )
        }
    }
}
