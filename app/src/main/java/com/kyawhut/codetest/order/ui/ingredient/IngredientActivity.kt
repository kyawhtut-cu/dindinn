package com.kyawhut.codetest.order.ui.ingredient

import androidx.activity.viewModels
import com.kyawhut.codetest.order.R
import com.kyawhut.codetest.order.databinding.ActivityIngredientBinding
import com.kyawhut.codetest.share.base.BaseActivityWithVM
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@AndroidEntryPoint
class IngredientActivity : BaseActivityWithVM<ActivityIngredientBinding, IngredientViewModel>() {

    override val layoutID: Int
        get() = R.layout.activity_ingredient

    override val vm: IngredientViewModel by viewModels()
}
