package com.kyawhut.codetest.order.ui.ingredient

import com.kyawhut.codetest.order.data.model.CategoryModel
import com.kyawhut.codetest.order.data.model.CategoryModel.Companion.toCategoryModel
import com.kyawhut.codetest.order.data.model.IngredientModel
import com.kyawhut.codetest.order.data.model.IngredientModel.Companion.toIngredientModel
import com.kyawhut.codetest.order.data.network.API
import com.kyawhut.codetest.share.utils.Extension.backgroundThread
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * @author kyawhtut
 * @date 11/9/21
 */
class IngredientRepositoryImpl @Inject constructor(
    private val api: API
) : IngredientRepository {

    override fun getCategoryList(): Single<List<CategoryModel>> {
        return api.getCategoryList().map {
            it.map {
                it.toCategoryModel()
            }
        }.backgroundThread()
    }

    override fun getIngredientListByCategoryID(categoryID: String): Single<List<IngredientModel>> {
        return api.getIngredientList().map {
            it.filter {
                "${it.categoryID}" == categoryID
            }.map {
                it.toIngredientModel()
            }
        }.backgroundThread()
    }
}
