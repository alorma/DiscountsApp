package com.alorma.discounts.ui.discountdetail

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.barcode.DiscountBarcodeGenerator
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.domain.BarcodeFormat
import com.alorma.discounts.ui.base.BaseViewModel
import com.alorma.discounts.ui.discountslist.DiscountViewMapper
import com.alorma.discounts.ui.discountslist.DiscountViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DiscountDetailViewModel(
    private val discountsDao: DiscountsDao,
    private val mapper: DiscountViewMapper,
    private val generator: DiscountBarcodeGenerator
) : BaseViewModel<DiscountDetailViewModel.View>() {

    fun onInit(code: String) {
        viewModelScope.launch {
            val discount = async {
                val entity = discountsDao.getByCode(code)
                mapper.mapItem(entity)
            }

            discount.await().let { discountModel ->
                view?.showDiscount(discountModel)
                loadBarcode(discountModel)
            }
        }
    }

    private fun loadBarcode(discountModel: DiscountViewModel) {
        viewModelScope.launch {
            val bitmap = async {
                generator.getBarcode(discountModel.code, BarcodeFormat.FORMAT_CODE_128)
            }

            bitmap.await()?.let {
                view?.showBitmap(it)
            }
        }
    }

    interface View : BaseView {
        fun showDiscount(discountViewModel: DiscountViewModel)
        fun showBitmap(bitmap: Bitmap)
    }
}