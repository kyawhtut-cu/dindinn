package com.kyawhut.codetest.share.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kyawhut.codetest.share.utils.GlideApp

/**
 * @author kyawhtut
 * @date 11/8/21
 */
object ImageBinding {

    @JvmStatic
    @BindingAdapter("imageURL")
    fun ImageView.loadImage(imageURL: String?) {
        if (imageURL == null) return
        GlideApp.with(this.context)
            .load(imageURL.replace("http", "https"))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}
