package com.alorma.discounts.ui.discountslist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseActivity
import com.alorma.discounts.ui.discountdetail.DiscountDetailActivity
import com.alorma.discounts.ui.newdiscount.NewDiscountActivity
import kotlinx.android.synthetic.main.discounts_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountsListActivity : BaseActivity(), DiscountsViewModel.View {

    private val discountsViewModel by viewModel<DiscountsViewModel>()
    private val adapter: DiscountsAdapter = DiscountsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discounts_list)

        adapter.callback = { discountsViewModel.onDiscountClick(it) }
        discountsViewModel.view = this
        discountsViewModel.onInit()

        addDiscount.setOnClickListener {
            discountsViewModel.onCreateNew()
        }

        discountsList.adapter = adapter
        discountsList.layoutManager = LinearLayoutManager(this)
    }

    override fun showDiscounts(discounts: List<DiscountViewModel>) {
        adapter.items = discounts
    }

    override fun openNewDiscount() {
        val intent = NewDiscountActivity.build(this)
        startActivity(intent)
    }

    override fun openDetail(code: String) {
        val intent = DiscountDetailActivity.buildIntent(this, code)
        startActivity(intent)
    }
}
