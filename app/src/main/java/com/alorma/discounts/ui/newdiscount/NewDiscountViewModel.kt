package com.alorma.discounts.ui.newdiscount

import androidx.lifecycle.viewModelScope
import com.alorma.discounts.domain.DiscountType
import com.alorma.discounts.domain.Result
import com.alorma.discounts.domain.SaveDiscount
import com.alorma.discounts.domain.usecase.SaveDiscountUseCase
import com.alorma.discounts.ui.barcode.BarcodeCaptureResultData
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class NewDiscountViewModel(
    private val saveDiscountUseCase: SaveDiscountUseCase
) : BaseViewModel<NewDiscountViewModel.View>() {

    private var saveDiscountParams: SaveDiscountParams = SaveDiscountParams()

    fun onBarcodeCaptured(capture: BarcodeCaptureResultData) {
        saveDiscountParams = saveDiscountParams.copy(code = capture.code, format = capture.format)

        view?.showBarcodeData(capture.code, capture.format.name)
    }

    fun onDiscountTypeSelected(discountType: DiscountTypeViewModel) {
        saveDiscountParams = saveDiscountParams.copy(discountType = mapDiscountType(discountType))
    }

    fun onExpirationDateChanged(date: Calendar) {
        saveDiscountParams = saveDiscountParams.copy(expirationDate = date.time)
    }

    fun onSave(title: String, quantity: String) {
        saveDiscountParams = saveDiscountParams.copy()
        viewModelScope.launch {
            if (saveDiscountParams.isValid()) {
                val saveDiscount = SaveDiscount(
                    saveDiscountParams.code!!,
                    saveDiscountParams.format!!,
                    quantity.toDoubleOrNull(),
                    saveDiscountParams.discountType,
                    saveDiscountParams.expirationDate,
                    title
                )

                when (val it = saveDiscountUseCase.save(saveDiscount)) {
                    is Result.Success<*> -> view?.close()
                    is Result.Error -> {
                        view?.onError(it.t)
                    }
                }
            }
        }
    }

    private fun mapDiscountType(discountType: DiscountTypeViewModel): DiscountType = if (discountType.symbol == "%") {
        DiscountType.Percentage
    } else {
        DiscountType.Currency
    }

    interface View : BaseView {
        fun showBarcodeData(code: String, format: String)

        fun onError(t: Throwable)

        fun close()
    }
}