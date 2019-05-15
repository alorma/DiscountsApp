package com.alorma.discounts.ui.discountslist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseActivity
import kotlinx.android.synthetic.main.discounts_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountsListActivity : BaseActivity(), DiscountsViewModel.View {

    private val discountsViewModel by viewModel<DiscountsViewModel>()
    private val adapter: DiscountsAdapter = DiscountsAdapter()

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

        discountsList.adapter = adapter
        discountsList.layoutManager = LinearLayoutManager(this)
    }

    override fun showDiscounts(discounts: List<DiscountViewModel>) {
        adapter.items = discounts
    }
}
