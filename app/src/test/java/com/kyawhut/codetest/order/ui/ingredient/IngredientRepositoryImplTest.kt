package com.kyawhut.codetest.order.ui.ingredient

import com.kyawhut.codetest.order.data.model.CategoryModel
import com.kyawhut.codetest.order.data.model.IngredientModel
import com.kyawhut.codetest.order.data.network.API
import com.kyawhut.codetest.order.utils.MockAPI.mockAPI
import com.kyawhut.codetest.order.utils.RxSchedulerRule
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author kyawhtut
 * @date 11/11/21
 */
class IngredientRepositoryImplTest {

    @Rule
    @JvmField
    val rxRule = RxSchedulerRule()

    private lateinit var api: API

    private lateinit var repository: IngredientRepository

    @Before
    fun setup() {
        api = mockAPI()
        repository = IngredientRepositoryImpl(api)
    }

    @Test
    fun `on request category list`() {
        val categoryObserver = TestObserver<List<CategoryModel>>()

        repository.getCategoryList().subscribe(categoryObserver)

        categoryObserver.assertComplete()
        categoryObserver.assertNoErrors()
        categoryObserver.assertValueCount(1)
        Assert.assertEquals(3, categoryObserver.values().first().size)
    }

    private fun `on request ingredient list by category id`(categoryID: Int): TestObserver<List<IngredientModel>> {
        val ingredientObserver = TestObserver<List<IngredientModel>>()

        repository.getIngredientListByCategoryID("$categoryID").subscribe(ingredientObserver)

        return ingredientObserver
    }

    @Test
    fun `on request ingredient list by category id`() {
        val ingredientObserver = `on request ingredient list by category id`(1)

        ingredientObserver.assertComplete()
        ingredientObserver.assertNoErrors()
        ingredientObserver.assertValueCount(1)
        Assert.assertEquals(true, ingredientObserver.values().first().isNotEmpty())
    }

    @Test
    fun `on request ingredient list by category id but empty list`() {
        val ingredientObserver = `on request ingredient list by category id`(100)

        ingredientObserver.assertComplete()
        ingredientObserver.assertNoErrors()
        ingredientObserver.assertValueCount(1)
        Assert.assertEquals(true, ingredientObserver.values().first().isEmpty())
    }
}
