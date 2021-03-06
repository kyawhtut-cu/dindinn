package com.kyawhut.codetest.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kyawhut.codetest.order.adapter.viewholder.IngredientViewHolder
import com.kyawhut.codetest.order.data.model.IngredientModel
import com.kyawhut.codetest.order.databinding.ItemIngredientBinding
import com.kyawhut.codetest.share.adapter.BaseAdapter
import com.kyawhut.codetest.share.adapter.BaseViewHolder
import timber.log.Timber

/**
 * @author kyawhtut
 * @date 11/9/21
 */
class IngredientAdapter : BaseAdapter<IngredientModel>() {

    override val predicate: (IngredientModel, String) -> Boolean = { item, constraint ->
        item.name.contains(constraint, ignoreCase = true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*, IngredientModel> {
        return IngredientViewHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
