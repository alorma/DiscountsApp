package com.alorma.discounts.data.barcode

import com.alorma.discounts.domain.BarcodeFormat

class DrawBarcodeFormatMapper {

    fun map(formatCode: BarcodeFormat): com.google.zxing.BarcodeFormat = when (formatCode) {
        BarcodeFormat.FORMAT_CODE_128 -> com.google.zxing.BarcodeFormat.CODE_128
        BarcodeFormat.FORMAT_CODE_39 -> com.google.zxing.BarcodeFormat.CODE_39
        BarcodeFormat.FORMAT_CODE_93 -> com.google.zxing.BarcodeFormat.CODE_93
        BarcodeFormat.FORMAT_CODABAR -> com.google.zxing.BarcodeFormat.CODABAR
        BarcodeFormat.FORMAT_DATA_MATRIX -> com.google.zxing.BarcodeFormat.DATA_MATRIX
        BarcodeFormat.FORMAT_EAN_13 -> com.google.zxing.BarcodeFormat.EAN_13
        BarcodeFormat.FORMAT_EAN_8 -> com.google.zxing.BarcodeFormat.EAN_8
        BarcodeFormat.FORMAT_ITF -> com.google.zxing.BarcodeFormat.ITF
        BarcodeFormat.FORMAT_QR_CODE -> com.google.zxing.BarcodeFormat.QR_CODE
        BarcodeFormat.FORMAT_UPC_A -> com.google.zxing.BarcodeFormat.UPC_A
        BarcodeFormat.FORMAT_UPC_E -> com.google.zxing.BarcodeFormat.UPC_E
        BarcodeFormat.FORMAT_AZTEC -> com.google.zxing.BarcodeFormat.AZTEC
    }

    fun map(formatCode: String): com.google.zxing.BarcodeFormat = when (formatCode) {
        BarcodeFormat.FORMAT_CODE_128.name -> com.google.zxing.BarcodeFormat.CODE_128
        BarcodeFormat.FORMAT_CODE_39.name -> com.google.zxing.BarcodeFormat.CODE_39
        BarcodeFormat.FORMAT_CODE_93.name -> com.google.zxing.BarcodeFormat.CODE_93
        BarcodeFormat.FORMAT_CODABAR.name -> com.google.zxing.BarcodeFormat.CODABAR
        BarcodeFormat.FORMAT_DATA_MATRIX.name -> com.google.zxing.BarcodeFormat.DATA_MATRIX
        BarcodeFormat.FORMAT_EAN_13.name -> com.google.zxing.BarcodeFormat.EAN_13
        BarcodeFormat.FORMAT_EAN_8.name -> com.google.zxing.BarcodeFormat.EAN_8
        BarcodeFormat.FORMAT_ITF.name -> com.google.zxing.BarcodeFormat.ITF
        BarcodeFormat.FORMAT_QR_CODE.name -> com.google.zxing.BarcodeFormat.QR_CODE
        BarcodeFormat.FORMAT_UPC_A.name -> com.google.zxing.BarcodeFormat.UPC_A
        BarcodeFormat.FORMAT_UPC_E.name -> com.google.zxing.BarcodeFormat.UPC_E
        BarcodeFormat.FORMAT_AZTEC.name -> com.google.zxing.BarcodeFormat.AZTEC
        else -> throw IllegalStateException()
    }
}