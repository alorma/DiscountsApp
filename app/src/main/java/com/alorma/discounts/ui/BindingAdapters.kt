package com.alorma.discounts.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import net.codecision.glidebarcode.model.Barcode

@BindingAdapter("loadBarcode")
fun bindBarcode(imageView: ImageView, barcode: Barcode?) {
    barcode?.let {
        Glide.with(imageView.context)
            .load(it)
            .into(imageView)
    }
}