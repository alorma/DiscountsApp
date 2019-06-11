package com.alorma.discounts.extensions

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.alorma.discounts.R

fun Fragment.setupBack(toolbar: Toolbar, id: Int = R.id.nav_graph) {
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
    toolbar.setNavigationOnClickListener {
        requireActivity().navigateUp(id)
    }
}

internal fun Fragment.navigateUp(id: Int = R.id.nav_graph) {
    requireActivity().navigateUp()
}

internal fun Activity.navigateUp(id: Int = R.id.nav_graph) {
    val successful = findNavController(id).popBackStack()
    if (!successful) {
        finish()
    }
}