package com.alorma.discounts.data.resources

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

class ResourcesProvider(private val context: Context) {

    fun getColor(@ColorRes colorRes: Int): Int = ContextCompat.getColor(context, colorRes)

    fun getUiMode(): Int = context.resources.configuration.uiMode

    fun isNight(): Boolean = getUiMode() and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}