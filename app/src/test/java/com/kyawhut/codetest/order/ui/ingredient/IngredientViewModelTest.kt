package com.kyawhut.codetest.order.ui.ingredient

import com.kyawhut.codetest.order.data.network.API
import com.kyawhut.codetest.order.utils.MockAPI.mockAPI
import com.kyawhut.codetest.order.utils.RxSchedulerRule
import com.kyawhut.codetest.share.network.utils.NetworkStatus
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author kyawhtut
 * @date 11/11/21
 */
class IngredientViewModelTest {

    @Rule
    @JvmField
    val rxRule = RxSchedulerRule()

    private lateinit var api: API

    private lateinit var repository: IngredientRepository

    private lateinit var viewModel: IngredientViewModel

    @Before
    fun setup() {
        api = mockAPI()
        repository = IngredientRepositoryImpl(api)
        viewModel = IngredientViewModel(repository)
    }

    @Test
    fun `request category list`() {
        viewModel.getCategoryList {
            when {
                it.isLoading -> {
                    Assert.assertEquals(it.networkStatus, NetworkStatus.LOADING)
                }
                it.isSuccess -> {
                    Assert.assertEquals(it.data != null, true)
                }
                it.isError -> {
                    Assert.assertEquals(it.networkStatus, NetworkStatus.ERROR)
                }
            }
        }
    }

    @Test
    fun `request ingredient list by category id`() {
        viewModel.getIngredientListByCategoryID("1") {
            when {
                it.isLoading -> {
                    Assert.assertEquals(it.networkStatus, NetworkStatus.LOADING)
                }
                it.isSuccess -> {
                    Assert.assertEquals(it.data.isNullOrEmpty(), false)
                }
                it.isError -> {
                    Assert.assertEquals(it.networkStatus, NetworkStatus.ERROR)
                }
            }
        }
    }

    @Test
    fun `request empty ingredient list by category id`() {
        viewModel.getIngredientListByCategoryID("100") {
            when {
                it.isLoading -> {
                    Assert.assertEquals(it.networkStatus, NetworkStatus.LOADING)
                }
                it.isSuccess -> {
                    Assert.assertEquals(it.data.isNullOrEmpty(), true)
                }
                it.isError -> {
                    Assert.assertEquals(it.networkStatus, NetworkStatus.ERROR)
                }
            }
        }
    }
}
