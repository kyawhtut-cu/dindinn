package com.kyawhut.codetest.order.utils

import com.kyawhut.codetest.order.BuildConfig
import com.kyawhut.codetest.order.data.network.API
import com.kyawhut.codetest.share.network.utils.NetworkUtil

/**
 * @author kyawhtut
 * @date 11/10/21
 */
object MockAPI {

    fun mockAPI(): API {
        return NetworkUtil.createService(
            API::class,
            BuildConfig.BASE_URL,
        )
    }
}
