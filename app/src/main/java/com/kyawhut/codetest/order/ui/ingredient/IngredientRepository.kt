package com.kyawhut.codetest.order.ui.ingredient

import com.kyawhut.codetest.order.data.model.CategoryModel
import com.kyawhut.codetest.order.data.model.IngredientModel
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * @author kyawhtut
 * @date 11/9/21
 */
interface IngredientRepository {

    fun getCategoryList(): Single<List<CategoryModel>>

    fun getIngredientListByCategoryID(categoryID: String): Single<List<IngredientModel>>
}
