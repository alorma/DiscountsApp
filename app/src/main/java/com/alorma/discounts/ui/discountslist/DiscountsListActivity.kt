package com.alorma.discounts.ui.discountslist

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseActivity
import com.alorma.discounts.ui.newdiscount.NewDiscountActivity
import kotlinx.android.synthetic.main.discounts_list.addDiscount

class DiscountsListActivity : BaseActivity() {

    private val discountsViewModel: DiscountsViewModel by lazy {
        ViewModelProviders.of(this).get(DiscountsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discounts_list)

        addDiscount.setOnClickListener {
            val intent = NewDiscountActivity.build(this)
            startActivity(intent)
        }
    }
}
