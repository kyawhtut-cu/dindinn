package com.kyawhut.codetest.share.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author kyawhtut
 * @date 11/8/21
 */
object Extension {

    fun <T> Single<T>.backgroundThread() = this.subscribeOn(Schedulers.io())

    fun <T> Single<T>.mainThread() = this.observeOn(AndroidSchedulers.mainThread())

    fun <T> Single<T>.bothThread() = this.backgroundThread().mainThread()
}
