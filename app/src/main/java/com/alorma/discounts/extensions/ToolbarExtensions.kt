package com.alorma.discounts.extensions

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.alorma.discounts.R

fun Fragment.setupBack(toolbar: Toolbar) {
    val navController = findNavController()
    val appbarConfig = AppBarConfiguration(navController.graph)
    toolbar.setupWithNavController(navController, appbarConfig)
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
}