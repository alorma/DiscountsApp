package com.alorma.discounts.ui.newdiscount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alorma.discounts.data.barcode.DrawBarcodeFormatMapper
import com.alorma.discounts.domain.Result
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
        saveDiscountParams = saveDiscountParams.copy(
            barcode = capture
        )

        val barcode = Barcode(capture.code, drawBarcodeFormatMapper.map(capture.format))
        _barcode.postValue(barcode)
    }

    fun onExpirationDateChanged(date: Calendar) {
        saveDiscountParams = saveDiscountParams.copy(expirationDate = date.time)
    }

    fun onSave(text: String) {
        if (text.isBlank()) {
            return
        }
        saveDiscountParams = saveDiscountParams.copy(place = SavePlace("la-sirena", "La sirena"))
        saveDiscountParams = saveDiscountParams.copy(text = text)
        viewModelScope.launch {
            when (val it = saveDiscountUseCase.save(saveDiscountParams)) {
                is Result.Success<*> -> {
                    reset()
                    view?.close()
                }
                is Result.Fail -> {
                    val error = newDiscountViewMapper.mapError(it.t)
                    view?.onError(error)
                }
            }
        }
    }

    fun reset() {
        _barcode.postValue(null)
    }

    fun onPlaceSelected(savePlace: SavePlace) {
        saveDiscountParams = saveDiscountParams.copy(place = savePlace)
    }

    interface View : BaseView {
        fun onError(error: String)

        fun close()
    }
}