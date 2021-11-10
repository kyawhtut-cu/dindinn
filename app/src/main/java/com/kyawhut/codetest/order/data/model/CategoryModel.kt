package com.kyawhut.codetest.order.data.model

import com.kyawhut.codetest.order.data.network.response.CategoryResponse

/**
 * @author kyawhtut
 * @date 11/9/21
 */
data class CategoryModel(
    val categoryID: String,
    val categoryName: String,
    val createdAt: String,
) {

    companion object {
        fun CategoryResponse.toCategoryModel(): CategoryModel {
            return CategoryModel(categoryID, categoryName, createdAt)
        }
    }
}
