package com.alorma.discounts.ui.barcode

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.observe
import com.alorma.discounts.R
import com.alorma.discounts.domain.BarcodeFormat
import com.alorma.discounts.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_barcode_reader.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uk.co.brightec.kbarcode.camera.OnCameraErrorListener


class BarcodeCaptureActivity : BaseActivity(), BarcodeCaptureViewModel.View {
    private val barcodeCaptureViewModel: BarcodeCaptureViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_reader)

        barcodeCaptureViewModel.view = this

        lifecycle.addObserver(barcodeView)
        barcodeView.barcode.observe(this) {
            barcodeCaptureViewModel.onCaptured(it)
        }
        barcodeView.onCameraErrorListener = OnCameraErrorListener { error ->
            Log.e("Alorma", error::class.simpleName)
        }
    }

    override fun closeWithData(code: String, format: BarcodeFormat) {
        val intent = Intent().putExtra(EXTRA_RETURN, BarcodeCaptureResultData(code, format))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val EXTRA_RETURN = "extra_return"

        fun buildIntent(context: Context): Intent =
            Intent(context, BarcodeCaptureActivity::class.java)
    }
}