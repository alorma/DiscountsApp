package com.alorma.discounts.ui.newdiscount

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.alorma.discounts.R
import com.alorma.discounts.ui.barcode.BarcodeCaptureActivity
import com.alorma.discounts.ui.barcode.BarcodeCaptureResultData
import com.alorma.discounts.ui.base.BaseActivity
import kotlinx.android.synthetic.main.new_discount_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewDiscountActivity : BaseActivity(), NewDiscoutViewModel.View {
    private val newDiscoutViewModel: NewDiscoutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_discount_activity)

        newDiscoutViewModel.view = this

        saveButton.setOnClickListener {
            finish()
        }

        codeField.actionListener = {
            runWithPermissions(Permission.CAMERA) {
                val intent = BarcodeCaptureActivity.buildIntent(this)
                startActivityForResult(intent, RC_CAPTURE_BARCODE)
            }
        }

        placeField.actionListener = {
            // Listener to select place
        }
    }

    override fun showBarcodeData(code: String, format: String) {
        codeField.setText(code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_CAPTURE_BARCODE && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<BarcodeCaptureResultData>(BarcodeCaptureActivity.EXTRA_RETURN)?.let {
                newDiscoutViewModel.onBarcodeCaptured(it)
            }
        }
    }

    companion object {
        private const val RC_CAPTURE_BARCODE = 1131
        fun build(context: Context): Intent = Intent(context, NewDiscountActivity::class.java)
    }
}