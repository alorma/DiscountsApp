package com.alorma.discounts.ui.discountdetail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseActivity
import com.alorma.discounts.ui.discountslist.DiscountViewModel
import kotlinx.android.synthetic.main.discount_detail_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountDetailActivity : BaseActivity(), DiscountDetailViewModel.View {
    private val detailViewModel: DiscountDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discount_detail_activity)

        detailViewModel.view = this
        intent?.extras?.getString(EXTRA_ID)?.let { discountCode ->
            detailViewModel.onInit(discountCode)
        }
    }

    override fun showDiscount(discountViewModel: DiscountViewModel.Item) {
        toolbar.title = discountViewModel.title
        code.text = discountViewModel.code
    }

    override fun showBitmap(bitmap: Bitmap) {
        barcodeImage.setImageBitmap(bitmap)
    }

    companion object {
        private const val EXTRA_ID = "extra_id"
        fun buildIntent(context: Context, code: String): Intent =
            Intent(context, DiscountDetailActivity::class.java)
                .putExtra(EXTRA_ID, code)
    }

}