package com.alorma.discounts.ui.discountslist

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.dao.DiscountsDao
import com.alorma.discounts.ui.base.BaseViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class DiscountsViewModel(
    private val discountsDao: DiscountsDao,
    private val mapper: DiscountViewMapper
) : BaseViewModel<DiscountsViewModel.View>() {

    fun onInit() {
        viewModelScope.launch {
            loadItems()
        }
    }

    private suspend fun loadItems() {
        val discountItems = discountsDao.getAll()
        val models = mapper.map(discountItems)
        view?.showDiscounts(models)
    }

    fun onCreateNew() {
        view?.openNewDiscount()
    }

    fun onDiscountClick(discount: DiscountViewModel.Item) {
        viewModelScope.launch {
            val bitmap = async {
                val writer = MultiFormatWriter()
                var matrix: BitMatrix? = null

                val width = 400
                val height = 200

                try {
                    matrix = writer.encode(discount.code, BarcodeFormat.QR_CODE, width, height)
                } catch (e: WriterException) {
                    e.printStackTrace()
                }

                matrix?.let {
                    val pixels = IntArray(width * height)
                    for (y in 0 until height) {
                        val offset = y * width
                        for (x in 0 until width) {
                            pixels[offset + x] = if (matrix.get(x, y)) Color.BLACK else Color.WHITE
                        }
                    }
                    Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)?.apply {
                        setPixels(pixels, 0, width, 0, 0, width, height)
                    }
                }
            }

            bitmap.await()?.let {
                view?.showBitmap(it)
            }
        }
    }

    interface View : BaseView {
        fun showDiscounts(discounts: List<DiscountViewModel>)
        fun showBitmap(await: Bitmap)

        fun openNewDiscount()
    }
}