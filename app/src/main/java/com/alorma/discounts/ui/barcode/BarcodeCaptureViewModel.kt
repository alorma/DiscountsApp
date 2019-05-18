package com.alorma.discounts.ui.barcode

import com.alorma.discounts.data.barcode.CameraBarcodeFormatMapper
import com.alorma.discounts.domain.BarcodeFormat
import com.alorma.discounts.ui.base.BaseViewModel
import uk.co.brightec.kbarcode.Barcode

class BarcodeCaptureViewModel(
    private val cameraBarcodeMapper: CameraBarcodeFormatMapper
) : BaseViewModel<BarcodeCaptureViewModel.View>() {

    fun onCaptured(barcode: Barcode) {
        barcode.rawValue?.let { rawValue ->
            val format = cameraBarcodeMapper.map(barcode.format)
            view?.closeWithData(rawValue, format)
        }
    }

    interface View : BaseView {
        fun closeWithData(code: String, format: BarcodeFormat)
    }

}