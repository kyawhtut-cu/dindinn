package com.kyawhut.codetest.share.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author kyawhtut
 * @date 11/8/21
 */
abstract class BaseViewModel : ViewModel() {

    protected val disposable: Disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
