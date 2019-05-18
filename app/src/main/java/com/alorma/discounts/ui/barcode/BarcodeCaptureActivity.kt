package com.alorma.discounts.ui.barcode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.observe
import com.alorma.discounts.BuildConfig
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseActivity
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import kotlinx.android.synthetic.main.activity_barcode_reader.*
import uk.co.brightec.kbarcode.KBarcode
import uk.co.brightec.kbarcode.camera.OnCameraErrorListener


class BarcodeCaptureActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_reader)

        KBarcode.setDebugging(BuildConfig.DEBUG)

        lifecycle.addObserver(barcodeView)
        barcodeView.barcode.observe(this) {
            Log.i("Alorma", it.rawValue)
            Log.i("Alorma", mapFormat(it.format).name)
            Log.i("Alorma", ".......")


            Toast.makeText(this, "Barcode!", Toast.LENGTH_SHORT).show()
            finish()
        }
        barcodeView.onCameraErrorListener = OnCameraErrorListener { error ->
            Log.e("Alorma", error::class.simpleName)
        }
    }

    private fun mapFormat(formatCode: Int): Format {
        return when (formatCode) {
            FirebaseVisionBarcode.FORMAT_UNKNOWN -> Format.FORMAT_UNKNOWN
            FirebaseVisionBarcode.FORMAT_ALL_FORMATS -> Format.FORMAT_ALL_FORMATS
            FirebaseVisionBarcode.FORMAT_CODE_128 -> Format.FORMAT_CODE_128
            FirebaseVisionBarcode.FORMAT_CODE_39 -> Format.FORMAT_CODE_39
            FirebaseVisionBarcode.FORMAT_CODE_93 -> Format.FORMAT_CODE_93
            FirebaseVisionBarcode.FORMAT_CODABAR -> Format.FORMAT_CODABAR
            FirebaseVisionBarcode.FORMAT_DATA_MATRIX -> Format.FORMAT_DATA_MATRIX
            FirebaseVisionBarcode.FORMAT_EAN_13 -> Format.FORMAT_EAN_13
            FirebaseVisionBarcode.FORMAT_EAN_8 -> Format.FORMAT_EAN_8
            FirebaseVisionBarcode.FORMAT_ITF -> Format.FORMAT_ITF
            FirebaseVisionBarcode.FORMAT_QR_CODE -> Format.FORMAT_QR_CODE
            FirebaseVisionBarcode.FORMAT_UPC_A -> Format.FORMAT_UPC_A
            FirebaseVisionBarcode.FORMAT_UPC_E -> Format.FORMAT_UPC_E
            FirebaseVisionBarcode.FORMAT_PDF417 -> Format.FORMAT_PDF417
            FirebaseVisionBarcode.FORMAT_AZTEC -> Format.FORMAT_AZTEC
            else -> Format.FORMAT_UNKNOWN
        }
    }

    enum class Format {
        FORMAT_UNKNOWN,
        FORMAT_ALL_FORMATS,
        FORMAT_CODE_128,
        FORMAT_CODE_39,
        FORMAT_CODE_93,
        FORMAT_CODABAR,
        FORMAT_DATA_MATRIX,
        FORMAT_EAN_13,
        FORMAT_EAN_8,
        FORMAT_ITF,
        FORMAT_QR_CODE,
        FORMAT_UPC_A,
        FORMAT_UPC_E,
        FORMAT_PDF417,
        FORMAT_AZTEC
    }

    companion object {
        fun buildIntent(context: Context): Intent =
            Intent(context, BarcodeCaptureActivity::class.java)
    }
}