package com.kyawhut.codetest.share.network.utils

/**
 * @author kyawhtut
 * @date 7/31/21
 */
data class NetworkResponse<T>(
    val networkStatus: NetworkStatus,
    val data: T? = null,
    val error: NetworkError? = null
) {
    companion object {
        fun <T> loading(): NetworkResponse<T> {
            return NetworkResponse(NetworkStatus.LOADING)
        }

        fun <T> success(data: T): NetworkResponse<T> {
            return NetworkResponse(NetworkStatus.SUCCESS, data)
        }

        fun <T> error(error: NetworkError?): NetworkResponse<T> {
            return NetworkResponse(NetworkStatus.ERROR, error = error)
        }
    }

    val isSuccess: Boolean get() = networkStatus == NetworkStatus.SUCCESS

    val isLoading: Boolean get() = networkStatus == NetworkStatus.LOADING

    val isError: Boolean get() = networkStatus == NetworkStatus.ERROR

}

sealed class NetworkStatus {
    object LOADING : NetworkStatus()
    object SUCCESS : NetworkStatus()
    object ERROR : NetworkStatus()
}
