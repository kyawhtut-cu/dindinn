package com.kyawhut.codetest.share.base

import androidx.databinding.ViewDataBinding

/**
 * @author kyawhtut
 * @date 11/8/21
 */
abstract class BaseFragmentWithVM<VB : ViewDataBinding, VM : BaseViewModel> : BaseFragment<VB>() {

    abstract val vm: VM
}
