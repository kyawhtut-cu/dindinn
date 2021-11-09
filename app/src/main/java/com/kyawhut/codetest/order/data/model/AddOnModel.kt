package com.kyawhut.codetest.order.data.model

import com.kyawhut.codetest.order.data.network.response.AddOnResponse

/**
 * @author kyawhtut
 * @date 11/8/21
 */
data class AddOnModel(
    val id: Int,
    val title: String,
    val quantity: Int,
) {
    companion object {
        fun AddOnResponse.toAddOnModel(): AddOnModel {
            return AddOnModel(id, title, quantity)
        }
    }
}
