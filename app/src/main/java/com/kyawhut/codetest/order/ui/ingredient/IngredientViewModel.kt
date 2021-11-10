package com.kyawhut.codetest.order.ui.ingredient

import com.kyawhut.codetest.order.data.model.CategoryModel
import com.kyawhut.codetest.order.data.model.IngredientModel
import com.kyawhut.codetest.share.base.BaseViewModel
import com.kyawhut.codetest.share.network.utils.NetworkResponse
import com.kyawhut.codetest.share.network.utils.error
import com.kyawhut.codetest.share.utils.Extension.mainThread
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val repo: IngredientRepository
) : BaseViewModel() {

    fun getCategoryList(callback: (NetworkResponse<List<CategoryModel>>) -> Unit) {
        callback(NetworkResponse.loading())
        repo.getCategoryList().mainThread().subscribe({
            callback(NetworkResponse.success(it))
        }, {
            callback(NetworkResponse.error(it.error))
        }).addTo(disposable)
    }

    fun getIngredientListByCategoryID(
        categoryID: String,
        callback: (NetworkResponse<List<IngredientModel>>) -> Unit
    ) {
        callback(NetworkResponse.loading())
        repo.getIngredientListByCategoryID(categoryID).mainThread().subscribe({
            callback(NetworkResponse.success(it))
        }, {
            callback(NetworkResponse.error(it.error))
        }).addTo(disposable)
    }
}
