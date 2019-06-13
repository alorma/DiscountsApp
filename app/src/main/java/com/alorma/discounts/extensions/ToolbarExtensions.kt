package com.alorma.discounts.extensions

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.alorma.discounts.R

internal fun Fragment.setupBack(toolbar: Toolbar) {
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
    toolbar.setNavigationOnClickListener {
        navigateUp()
    }
}

internal fun Fragment.navigateUp() {
    requireActivity().navigateUp()
}

internal fun Activity.navigateUp() {
    val successful = findNavController(R.id.nav_host_fragment).popBackStack()
    if (!successful) {
        finish()
    }
}
