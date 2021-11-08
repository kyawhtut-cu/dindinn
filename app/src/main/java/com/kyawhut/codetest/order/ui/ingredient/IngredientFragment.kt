package com.kyawhut.codetest.order.ui.ingredient

import androidx.fragment.app.viewModels
import com.kyawhut.codetest.order.R
import com.kyawhut.codetest.order.databinding.FragmentIngredientBinding
import com.kyawhut.codetest.share.base.BaseFragmentWithVM
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author kyawhtut
 * @date 11/8/21
 */
@AndroidEntryPoint
class IngredientFragment : BaseFragmentWithVM<FragmentIngredientBinding, IngredientViewModel>() {

    override val layoutID: Int
        get() = R.layout.fragment_ingredient

    override val vm: IngredientViewModel by viewModels()
}
