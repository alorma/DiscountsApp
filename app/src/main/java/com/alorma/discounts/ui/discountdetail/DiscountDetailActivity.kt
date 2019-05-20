package com.alorma.discounts.ui.discountdetail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseActivity
import com.alorma.discounts.ui.discountslist.DiscountViewModel
import com.bumptech.glide.Glide
import com.google.zxing.BarcodeFormat
import kotlinx.android.synthetic.main.discount_detail_activity.*
import net.codecision.glidebarcode.model.Barcode
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountDetailActivity : BaseActivity(), DiscountDetailViewModel.View {
    private val detailViewModel by viewModel<DiscountDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.discount_detail_activity)

        detailViewModel.view = this
        intent?.extras?.getString(EXTRA_ID)?.let { discountCode ->
            detailViewModel.onInit(discountCode)
        }
    }

    override fun showDiscount(discountViewModel: DiscountViewModel) {
        toolbar.title = discountViewModel.title
        code.text = discountViewModel.code

        Glide.with(this)
            .load(Barcode("99501280211090169339", BarcodeFormat.CODE_128))
            .into(barcodeImage)
    }

    override fun showBitmap(bitmap: Bitmap) {

    }

    companion object {
        private const val EXTRA_ID = "extra_id"
        fun buildIntent(context: Context, code: String): Intent =
            Intent(context, DiscountDetailActivity::class.java)
                .putExtra(EXTRA_ID, code)
    }

}