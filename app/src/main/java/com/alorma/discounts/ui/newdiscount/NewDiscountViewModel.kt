package com.alorma.discounts.ui.newdiscount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.barcode.DrawBarcodeFormatMapper
import com.alorma.discounts.domain.DiscountType
import com.alorma.discounts.domain.Result
import com.alorma.discounts.domain.SaveDiscount
import com.alorma.discounts.domain.usecase.SaveDiscountUseCase
import com.alorma.discounts.ui.barcode.BarcodeCaptureResultData
import com.alorma.discounts.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import net.codecision.glidebarcode.model.Barcode
import java.util.*

class NewDiscountViewModel(
    private val saveDiscountUseCase: SaveDiscountUseCase,
    private val newDiscountViewMapper: NewDiscountViewMapper,
    private val drawBarcodeFormatMapper: DrawBarcodeFormatMapper
) : BaseViewModel<NewDiscountViewModel.View>() {

    private var _barcode = MutableLiveData<Barcode>()
    val barcode: LiveData<Barcode>
        get() = _barcode

    private var saveDiscountParams: SaveDiscountParams = SaveDiscountParams()

    fun onBarcodeCaptured(capture: BarcodeCaptureResultData) {
        saveDiscountParams = saveDiscountParams.copy(code = capture.code, format = capture.format)

        val barcode = Barcode(capture.code, drawBarcodeFormatMapper.map(capture.format))
        _barcode.postValue(barcode)
    }

    fun onDiscountTypeSelected(discountType: DiscountTypeViewModel) {
        saveDiscountParams = saveDiscountParams.copy(discountType = mapDiscountType(discountType))
    }

    fun onExpirationDateChanged(date: Calendar) {
        saveDiscountParams = saveDiscountParams.copy(expirationDate = date.time)
    }

    fun onSave(title: String, quantity: String) {
        saveDiscountParams = saveDiscountParams.copy(quantity = quantity.toDoubleOrNull())
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
                        val error = newDiscountViewMapper.mapError(it.t)
                        view?.onError(error)
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

    fun reset() {
        _barcode = MutableLiveData()
    }

    interface View : BaseView {
        fun onError(error: String)

        fun close()
    }
}