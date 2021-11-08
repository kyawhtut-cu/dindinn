package com.kyawhut.codetest.order.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.kyawhut.codetest.order.databinding.ViewOrderHeaderBinding

/**
 * @author kyawhtut
 * @date 11/8/21
 */
class ViewOrderHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        @JvmStatic
        @BindingAdapter("orderNumber")
        fun ViewOrderHeader.setOrderNumber(orderNumber: Int?) {
            this.orderNumber = "${orderNumber ?: 0}"
        }

        @JvmStatic
        @BindingAdapter("orderTime")
        fun ViewOrderHeader.setOrderTime(orderTime: String?) {
            this.orderTime = orderTime ?: ""
        }
    }

    private val vb: ViewOrderHeaderBinding = ViewOrderHeaderBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var orderNumber: String = ""
        set(value) {
            field = value
            vb.orderNumber = value
        }

    var orderTime: String = ""
        set(value) {
            field = value
            vb.orderTime = value
        }
}
