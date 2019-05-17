package com.alorma.discounts.data.barcode

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DiscountBarcodeGenerator {

    suspend fun getBarcode(discountCode: String): Bitmap? = withContext(Dispatchers.Default) {
        val writer = MultiFormatWriter()
        var matrix: BitMatrix? = null

        val width = 500
        val height = 100

        try {
            matrix = writer.encode("99501280211090169339", BarcodeFormat.CODE_128, width, height)
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
}