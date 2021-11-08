package com.kyawhut.codetest.order.ui.main

import com.kyawhut.codetest.order.R
import com.kyawhut.codetest.order.databinding.ActivityMainBinding
import com.kyawhut.codetest.share.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutID: Int
        get() = R.layout.activity_main
}
