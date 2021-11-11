package com.kyawhut.codetest.order.ui.component

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.kyawhut.codetest.order.databinding.ViewOrderHeaderBinding
import java.util.*

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

    private var _onExpired: (() -> Unit)? = null
    private var _onAlert: (() -> Unit)? = null
    private var _isAlerted: Boolean = false

    private val currentTime: Date
        get() = Date()

    private var countDownTimer: CountDownTimer? = null

    var expireAt: Date = Date()
    var alertAt: Date = Date()

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

    fun startTimer() {
        vb.isVisibleTimer = false
        if (expireAt.time - currentTime.time < 0) {
            _onExpired?.invoke()
            return
        }

        if (countDownTimer == null) {
            countDownTimer = object : CountDownTimer(expireAt.time - currentTime.time, 1000) {
                override fun onFinish() {
                }

                override fun onTick(millisUntilFinished: Long) {
                    if (currentTime.time >= alertAt.time && !_isAlerted) {
                        _isAlerted = true
                        _onAlert?.invoke()
                    }

                    vb.timer = "${millisUntilFinished / 1000}s"

                    if (millisUntilFinished / 1000 == 0L) {
                        vb.isVisibleTimer = false
                        _onExpired?.invoke()
                    }
                }
            }
        }
        vb.isVisibleTimer = true
        countDownTimer?.start()
    }

    fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    fun setOnExpiredListener(onExpired: () -> Unit) {
        this._onExpired = onExpired
    }

    fun setOnAlertListener(onAlert: () -> Unit) {
        this._onAlert = onAlert
    }

    override fun onDetachedFromWindow() {
        stopTimer()
        super.onDetachedFromWindow()
    }
}
