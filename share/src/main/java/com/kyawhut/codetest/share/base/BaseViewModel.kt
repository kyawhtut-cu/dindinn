package com.kyawhut.codetest.share.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * @author kyawhtut
 * @date 11/8/21
 */
abstract class BaseViewModel : ViewModel() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
