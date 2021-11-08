package com.kyawhut.codetest.share.base

import androidx.databinding.ViewDataBinding

/**
 * @author kyawhtut
 * @date 11/8/21
 */
abstract class BaseActivityWithVM<VB : ViewDataBinding, VM : BaseViewModel> : BaseActivity<VB>() {

    abstract val vm: VM

}
