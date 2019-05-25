package com.alorma.discounts.ui.discountdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alorma.discounts.databinding.DiscountDetailActivityBinding
import com.alorma.discounts.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountDetailActivity : BaseActivity(), DiscountDetailViewModel.View {

    private lateinit var binding: DiscountDetailActivityBinding
    private val detailViewModel by viewModel<DiscountDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiscountDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel

        detailViewModel.view = this

        intent?.extras?.getString(EXTRA_ID)?.let { discountCode ->
            detailViewModel.code.postValue(discountCode)
        }
    }

    companion object {
        private const val EXTRA_ID = "extra_id"
        fun buildIntent(context: Context, code: String): Intent =
            Intent(context, DiscountDetailActivity::class.java)
                .putExtra(EXTRA_ID, code)
    }

}