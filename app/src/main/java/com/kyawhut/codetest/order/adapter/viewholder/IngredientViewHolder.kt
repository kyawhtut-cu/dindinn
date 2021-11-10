package com.kyawhut.codetest.order.adapter.viewholder

import com.kyawhut.codetest.order.data.model.IngredientModel
import com.kyawhut.codetest.order.databinding.ItemIngredientBinding
import com.kyawhut.codetest.share.adapter.BaseViewHolder

/**
 * @author kyawhtut
 * @date 11/9/21
 */
class IngredientViewHolder(
    vb: ItemIngredientBinding
) : BaseViewHolder<ItemIngredientBinding, IngredientModel>(vb) {

    override fun bind() {
        data?.let {
            vb.ingredient = it
            vb.executePendingBindings()
        }
    }
}
