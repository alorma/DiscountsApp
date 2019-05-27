package com.alorma.discounts.ui.newdiscount.barcode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.alorma.discounts.R
import com.alorma.discounts.domain.BarcodeFormat
import com.alorma.discounts.ui.base.BaseFragment
import com.alorma.discounts.ui.newdiscount.form.NewDiscountViewModel
import kotlinx.android.synthetic.main.barcode_reader_fragment.barcodeView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import uk.co.brightec.kbarcode.BarcodeView
import uk.co.brightec.kbarcode.Options
import uk.co.brightec.kbarcode.camera.OnCameraErrorListener

class BarcodeCaptureFragment : BaseFragment(), BarcodeCaptureViewModel.View {

    private val newDiscountViewModel by sharedViewModel<NewDiscountViewModel>()
    private val barcodeCaptureViewModel by viewModel<BarcodeCaptureViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.barcode_reader_fragment, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        barcodeCaptureViewModel.view = this

        val options = Options.Builder()
                .scaleType(BarcodeView.CENTER_CROP)
                .build()

        barcodeView.setOptions(options)
        lifecycle.addObserver(barcodeView)
        barcodeView.barcode.observe(this) {
            barcodeCaptureViewModel.onCaptured(it)
        }
        barcodeView.onCameraErrorListener = OnCameraErrorListener { error ->
            Log.e("Alorma", error::class.simpleName)
        }
    }

    override fun closeWithData(code: String, format: BarcodeFormat) {
        val barcode = BarcodeCaptureResultData(code, format)

        newDiscountViewModel.onBarcodeCaptured(barcode)

        findNavController().popBackStack()
    }
}