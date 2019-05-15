package com.alorma.discounts.ui.discountslist

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.observe
import com.alorma.discounts.R
import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.base.BaseActivity
import com.alorma.discounts.ui.newdiscount.NewDiscountActivity
import kotlinx.android.synthetic.main.discounts_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountsListActivity : BaseActivity() {

    private val discountsViewModel by viewModel<DiscountsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discounts_list)

        discountsViewModel.discounts.observe(this) {
            onDiscounts(it)
        }

        addDiscount.setOnClickListener {
            val intent = NewDiscountActivity.build(this)
            startActivity(intent)
        }
    }

    private fun onDiscounts(it: List<DiscountEntity>) {
        Toast.makeText(this, "Items: ${it.size}", Toast.LENGTH_SHORT).show()
    }
}
