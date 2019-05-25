package com.alorma.discounts.data.barcode

import com.alorma.discounts.domain.BarcodeFormat
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import java.security.InvalidParameterException

class CameraBarcodeFormatMapper {

    fun map(formatCode: Int): BarcodeFormat = when (formatCode) {
        FirebaseVisionBarcode.FORMAT_CODE_128 -> BarcodeFormat.FORMAT_CODE_128
        FirebaseVisionBarcode.FORMAT_CODE_39 -> BarcodeFormat.FORMAT_CODE_39
        FirebaseVisionBarcode.FORMAT_CODE_93 -> BarcodeFormat.FORMAT_CODE_93
        FirebaseVisionBarcode.FORMAT_CODABAR -> BarcodeFormat.FORMAT_CODABAR
        FirebaseVisionBarcode.FORMAT_DATA_MATRIX -> BarcodeFormat.FORMAT_DATA_MATRIX
        FirebaseVisionBarcode.FORMAT_EAN_13 -> BarcodeFormat.FORMAT_EAN_13
        FirebaseVisionBarcode.FORMAT_EAN_8 -> BarcodeFormat.FORMAT_EAN_8
        FirebaseVisionBarcode.FORMAT_ITF -> BarcodeFormat.FORMAT_ITF
        FirebaseVisionBarcode.FORMAT_QR_CODE -> BarcodeFormat.FORMAT_QR_CODE
        FirebaseVisionBarcode.FORMAT_UPC_A -> BarcodeFormat.FORMAT_UPC_A
        FirebaseVisionBarcode.FORMAT_UPC_E -> BarcodeFormat.FORMAT_UPC_E
        FirebaseVisionBarcode.FORMAT_AZTEC -> BarcodeFormat.FORMAT_AZTEC
        else -> throw InvalidParameterException("Format not supported")
    }

    fun map(formatCode: String): BarcodeFormat = when (formatCode) {
        BarcodeFormat.FORMAT_CODE_128.name -> BarcodeFormat.FORMAT_CODE_128
        BarcodeFormat.FORMAT_CODE_39.name -> BarcodeFormat.FORMAT_CODE_39
        BarcodeFormat.FORMAT_CODE_93.name -> BarcodeFormat.FORMAT_CODE_93
        BarcodeFormat.FORMAT_CODABAR.name -> BarcodeFormat.FORMAT_CODABAR
        BarcodeFormat.FORMAT_DATA_MATRIX.name -> BarcodeFormat.FORMAT_DATA_MATRIX
        BarcodeFormat.FORMAT_EAN_13.name -> BarcodeFormat.FORMAT_EAN_13
        BarcodeFormat.FORMAT_EAN_8.name -> BarcodeFormat.FORMAT_EAN_8
        BarcodeFormat.FORMAT_ITF.name -> BarcodeFormat.FORMAT_ITF
        BarcodeFormat.FORMAT_QR_CODE.name -> BarcodeFormat.FORMAT_QR_CODE
        BarcodeFormat.FORMAT_UPC_A.name -> BarcodeFormat.FORMAT_UPC_A
        BarcodeFormat.FORMAT_UPC_E.name -> BarcodeFormat.FORMAT_UPC_E
        BarcodeFormat.FORMAT_AZTEC.name -> BarcodeFormat.FORMAT_AZTEC
        else -> throw InvalidParameterException("Format not supported")
    }
}