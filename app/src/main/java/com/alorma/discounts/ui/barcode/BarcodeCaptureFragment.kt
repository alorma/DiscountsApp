package com.alorma.discounts.ui.barcode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.alorma.discounts.R
import com.alorma.discounts.domain.BarcodeFormat
import com.alorma.discounts.ui.base.BaseFragment
import kotlinx.android.synthetic.main.barcode_reader_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uk.co.brightec.kbarcode.camera.OnCameraErrorListener


class BarcodeCaptureFragment : BaseFragment(), BarcodeCaptureViewModel.View {
    private val barcodeCaptureViewModel by viewModel<BarcodeCaptureViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.barcode_reader_fragment, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        BarcodeCaptureResultData(code, format)
        TODO()
    }

    companion object {
        const val EXTRA_RETURN = "extra_return"
    }
}