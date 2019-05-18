package com.alorma.discounts.ui.newdiscount

import com.alorma.discounts.ui.barcode.BarcodeCaptureResultData
import com.alorma.discounts.ui.base.BaseViewModel

class NewDiscoutViewModel : BaseViewModel<NewDiscoutViewModel.View>() {

    fun onBarcodeCaptured(capture: BarcodeCaptureResultData) {
        view?.showBarcodeData(capture.code, capture.format.name)
    }

    interface View : BaseView {
        fun showBarcodeData(code: String, format: String)
    }
}