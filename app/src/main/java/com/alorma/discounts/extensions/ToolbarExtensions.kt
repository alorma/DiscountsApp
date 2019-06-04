package com.alorma.discounts.extensions

import android.app.Activity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.alorma.discounts.R

fun Toolbar.setupBack(activity: Activity) {
    setNavigationIcon(R.drawable.ic_arrow_back)
    setNavigationOnClickListener { activity.onBackPressed() }
}

fun Fragment.setupBack(toolbar: Toolbar) {
    val navController = findNavController()
    val appbarConfig = AppBarConfiguration(navController.graph)
    toolbar.setupWithNavController(navController, appbarConfig)
    toolbar.setOnMenuItemClickListener { it.onNavDestinationSelected(navController) }
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
}