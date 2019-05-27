package com.alorma.discounts.ui.newdiscount.barcode

import android.os.Parcelable
import com.alorma.discounts.domain.BarcodeFormat
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BarcodeCaptureResultData(
    val code: String,
    val format: BarcodeFormat
) : Parcelable