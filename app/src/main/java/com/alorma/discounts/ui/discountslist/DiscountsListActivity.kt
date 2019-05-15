package com.alorma.discounts.ui.discountslist

import android.os.Bundle
import android.widget.Toast
import com.alorma.discounts.R
import com.alorma.discounts.data.entity.DiscountEntity
import com.alorma.discounts.ui.base.BaseActivity
import kotlinx.android.synthetic.main.discounts_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountsListActivity : BaseActivity(), DiscountsViewModel.View {
    private val discountsViewModel by viewModel<DiscountsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discounts_list)

        discountsViewModel.view = this
        discountsViewModel.onInit()

        addDiscount.setOnClickListener {
            discountsViewModel.onCreateNew()
            /*
                val intent = NewDiscountActivity.build(this)
                startActivity(intent)
            */
        }
    }

    override fun showDiscounts(discounts: List<DiscountEntity>) {
        Toast.makeText(this, "Items: ${discounts.size}", Toast.LENGTH_SHORT).show()
    }
}
