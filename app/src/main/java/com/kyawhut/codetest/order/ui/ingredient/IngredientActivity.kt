package com.kyawhut.codetest.order.ui.ingredient

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.kyawhut.codetest.order.BR
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

    override val onClickListener: Int
        get() = BR.onClickListener

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

        vb.edtSearch.addTextChangedListener {
            ingredientAdapter.filter.filter(it.toString())
        }
    }

    private fun onCategoryNetworkState(state: NetworkResponse<List<CategoryModel>>) {
        vb.isLoading = state.isLoading
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
        } else if (state.isError) {
            state.error?.let { error ->
                Toast.makeText(
                    this,
                    error.message.takeIf { error.resId == 0 } ?: getString(error.resId),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun onIngredientNetworkState(state: NetworkResponse<List<IngredientModel>>) {
        vb.isLoading = state.isLoading
        if (state.isSuccess) {
            ingredientAdapter.clear()
            ingredientAdapter.addAll(state.data ?: listOf())
        } else if (state.isError) {
            state.error?.let { error ->
                Toast.makeText(
                    this,
                    error.message.takeIf { error.resId == 0 } ?: getString(error.resId),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.action_close -> finish()
        }
    }
}
