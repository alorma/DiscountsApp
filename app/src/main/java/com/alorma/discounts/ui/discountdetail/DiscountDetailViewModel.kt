package com.alorma.discounts.ui.discountdetail

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.barcode.DiscountBarcodeGenerator
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class DiscountDetailViewModel(
    private val generator: DiscountBarcodeGenerator
) : BaseViewModel<DiscountDetailViewModel.View>() {

    fun onInit(code: String) {
        viewModelScope.launch {
            generator.getBarcode(code)?.let {
                view?.showBitmap(it)
            }
        }
    }

    interface View : BaseView {
        fun showBitmap(bitmap: Bitmap)
    }
}