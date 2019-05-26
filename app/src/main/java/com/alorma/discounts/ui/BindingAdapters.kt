package com.alorma.discounts.ui

import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("loadBarcode")
fun bindBarcode(textView: TextView, barcode: Barcode?) {
    barcode?.let {
        textView.text = barcode.contents
    }
}