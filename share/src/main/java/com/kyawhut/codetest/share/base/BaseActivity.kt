package com.kyawhut.codetest.share.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author kyawhtut
 * @date 11/8/21
 */
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(), View.OnClickListener {

    abstract val layoutID: Int
    open val onClickListener: Int = -1

    protected lateinit var vb: VB

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = DataBindingUtil.setContentView(this, layoutID)
        vb.apply {
            if (onClickListener != -1) setVariable(onClickListener, this@BaseActivity)
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }
    }

    override fun onClick(v: View) {
    }
}
