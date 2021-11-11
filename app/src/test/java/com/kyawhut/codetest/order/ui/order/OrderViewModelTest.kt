package com.kyawhut.codetest.order.ui.order

import com.kyawhut.codetest.order.data.network.API
import com.kyawhut.codetest.order.utils.MockAPI.mockAPI
import com.kyawhut.codetest.order.utils.RxSchedulerRule
import com.kyawhut.codetest.share.network.utils.NetworkStatus
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

/**
 * @author kyawhtut
 * @date 11/10/21
 */
class OrderViewModelTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxSchedulerRule()

    private lateinit var api: API

    private lateinit var orderRepository: OrderRepository

    private lateinit var orderViewModel: OrderViewModel

    @Before
    fun setUp() {
        api = mockAPI()
        orderRepository = OrderRepositoryImpl(api)
        orderViewModel = OrderViewModel(orderRepository)
    }

    @Test
    fun `onOrderRequest set correct loading states`() {
        orderViewModel.getOrder {
            when {
                it.isLoading -> {
                    Assert.assertEquals(NetworkStatus.LOADING, it.networkStatus)
                }
                it.isSuccess -> {
                    Assert.assertEquals(false, it.data.isNullOrEmpty())
                }
                it.isError -> {
                    Assert.assertEquals(NetworkStatus.ERROR, it.networkStatus)
                }
            }
        }
    }
}
