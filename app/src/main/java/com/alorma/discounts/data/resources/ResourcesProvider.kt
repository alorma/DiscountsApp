package com.alorma.discounts.data.resources

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

class ResourcesProvider(private val context: Context) {

    fun getColor(@ColorRes colorRes: Int): Int = ContextCompat.getColor(context, colorRes)
}