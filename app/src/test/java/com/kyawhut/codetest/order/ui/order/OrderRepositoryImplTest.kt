package com.kyawhut.codetest.order.ui.order

import com.kyawhut.codetest.order.data.model.OrderModel
import com.kyawhut.codetest.order.data.network.API
import com.kyawhut.codetest.order.utils.MockAPI
import com.kyawhut.codetest.order.utils.RxSchedulerRule
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author kyawhtut
 * @date 11/10/21
 */
class OrderRepositoryImplTest {

    @Rule
    @JvmField
    val testSchedulerRule = RxSchedulerRule()

    lateinit var api: API

    private lateinit var orderRepository: OrderRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        api = MockAPI.mockAPI()
        orderRepository = OrderRepositoryImpl(api)
    }

    @Test
    fun `request order list`() {
        val orderResponseObserver = TestObserver<List<OrderModel>>()

        orderRepository.getOrder().subscribe(orderResponseObserver)

        orderResponseObserver.assertComplete()
        orderResponseObserver.assertNoErrors()
        orderResponseObserver.assertValueCount(1)
        Assert.assertEquals(2, orderResponseObserver.values().first().size)

    }
}