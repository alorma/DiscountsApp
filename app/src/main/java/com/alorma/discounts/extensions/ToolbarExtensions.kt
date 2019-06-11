package com.alorma.discounts.extensions

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alorma.discounts.R

fun Fragment.setupBack(toolbar: Toolbar) {
    val navController = findNavController()
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
    toolbar.setNavigationOnClickListener {
        navController.popBackStack()
    }
}