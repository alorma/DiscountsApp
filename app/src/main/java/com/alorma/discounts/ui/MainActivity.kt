package com.alorma.discounts.ui

import android.os.Bundle
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
    }
}