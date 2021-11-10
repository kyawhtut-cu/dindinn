package com.kyawhut.codetest.order.ui.ingredient

import android.os.Bundle
import androidx.activity.viewModels
import com.kyawhut.codetest.order.R
import com.kyawhut.codetest.order.adapter.IngredientAdapter
import com.kyawhut.codetest.order.data.model.CategoryModel
import com.kyawhut.codetest.order.data.model.IngredientModel
import com.kyawhut.codetest.order.databinding.ActivityIngredientBinding
import com.kyawhut.codetest.order.ui.component.CustomTab.Companion.tabItem
import com.kyawhut.codetest.share.base.BaseActivityWithVM
import com.kyawhut.codetest.share.network.utils.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@AndroidEntryPoint
class IngredientActivity : BaseActivityWithVM<ActivityIngredientBinding, IngredientViewModel>() {

    override val layoutID: Int
        get() = R.layout.activity_ingredient

    private val ingredientAdapter: IngredientAdapter by lazy {
        IngredientAdapter()
    }

    override val vm: IngredientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb.ingredientAdapter = ingredientAdapter

        vm.getCategoryList(::onCategoryNetworkState)

        vb.tabView.setOnTabItemClickedListener {
            vm.getIngredientListByCategoryID(
                "${it.id}",
                ::onIngredientNetworkState
            )
        }
    }

    private fun onCategoryNetworkState(state: NetworkResponse<List<CategoryModel>>) {
        if (state.isSuccess) {
            vb.apply {
                tabList = state.data?.mapIndexed { index, it ->
                    tabItem {
                        id = it.categoryID.toInt()
                        title = it.categoryName
                        isSelected = index == 0
                    }
                } ?: listOf()
                executePendingBindings()
            }

            if (!state.data.isNullOrEmpty()) {
                vm.getIngredientListByCategoryID(
                    state.data?.first()?.categoryID ?: "",
                    ::onIngredientNetworkState
                )
            }
        }
    }

    private fun onIngredientNetworkState(state: NetworkResponse<List<IngredientModel>>) {
        if (state.isSuccess) {
            ingredientAdapter.clear()
            ingredientAdapter.addAll(state.data ?: listOf())
        }
    }
}
