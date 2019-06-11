package com.alorma.discounts.ui.newdiscount

import android.os.Bundle
import androidx.navigation.findNavController
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseActivity

class NewDiscountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_discount_activity)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.new_discount_graph).navigateUp()
}