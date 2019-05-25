package com.alorma.discounts.ui.newdiscount

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.alorma.discounts.R
import com.alorma.discounts.data.barcode.CameraBarcodeFormatMapper
import com.alorma.discounts.extensions.hide
import com.alorma.discounts.ui.barcode.BarcodeCaptureActivity
import com.alorma.discounts.ui.barcode.BarcodeCaptureResultData
import com.alorma.discounts.ui.base.BaseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.new_discount_activity.*
import net.codecision.glidebarcode.model.Barcode
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewDiscountActivity : BaseActivity(), NewDiscountViewModel.View {

    private val newDiscountViewModel by viewModel<NewDiscountViewModel>()
    private val cameraBarcodeMapper: CameraBarcodeFormatMapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_discount_activity)

        newDiscountViewModel.view = this

        configCode()
/*
        configCode()
        configPlace()
        configDiscountType()
        configExpiration()
        saveButton.setOnClickListener {
            val title = titleField.editText?.text?.toString().orEmpty()
            val quantity = quantityField.editText?.text?.toString().orEmpty()

            newDiscountViewModel.onSave(title, quantity)
        }
*/
    }

    private fun configCode() {
        codeFieldImage.setOnClickListener {
            runWithPermissions(Permission.CAMERA) {
                if (it.isAllGranted()) {
                    val intent = BarcodeCaptureActivity.buildIntent(this)
                    startActivityForResult(intent, RC_CAPTURE_BARCODE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_CAPTURE_BARCODE && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<BarcodeCaptureResultData>(BarcodeCaptureActivity.EXTRA_RETURN)?.let {
                newDiscountViewModel.onBarcodeCaptured(it)
            }
        }
    }

    override fun showBarcodeData(barcode: Barcode) {
        Glide.with(codeFieldImage.context)
            .load(barcode)
            .into(codeFieldImage)
        codeFieldImage.background = null
        codeFieldPlaceholderText.hide()
    }

    override fun onError(error: String) {

    }

    override fun close() {

    }

    /*
        private fun configPlace() {
            placeField.actionListener = {
                // Listener to select place
            }
        }

        private fun configDiscountType() {
            val discountTypes = listOf(
                DiscountTypeViewModel("%", "percentage"),
                DiscountTypeViewModel("€", "Euro")
            )

            quantityTypeField.actionListener = {
                quantityTypeField.show(discountTypes, { discountType ->
                    discountType.symbol + " " + discountType.name
                }) {
                    newDiscountViewModel.onDiscountTypeSelected(it)
                }
            }
        }

        private fun configExpiration() {
            expirationField.actionListener = {
                MaterialDialog(this).show {
                    datePicker(
                        requireFutureDate = true,
                        currentDate = Calendar.getInstance().apply {
                            timeInMillis = System.currentTimeMillis()
                        }
                    ) { _, date ->
                        val day = date.get(Calendar.DAY_OF_MONTH)
                        val month = date.get(Calendar.MONTH)
                        val year = date.get(Calendar.YEAR)
                        this@NewDiscountActivity.expirationField.setText("$day/$month/$year")

                        newDiscountViewModel.onExpirationDateChanged(date)
                    }
                }
            }
        }

        override fun showBarcodeData(code: String, format: String) {
            codeField.setText(code)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == RC_CAPTURE_BARCODE && resultCode == Activity.RESULT_OK) {
                data?.getParcelableExtra<BarcodeCaptureResultData>(BarcodeCaptureActivity.EXTRA_RETURN)?.let {
                    newDiscountViewModel.onBarcodeCaptured(it)
                }
            }
        }

        override fun close() {
            setResult(Activity.RESULT_OK)
            finish()
        }

        override fun onError(errorMessage: String) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

     */
    companion object {
        private const val RC_CAPTURE_BARCODE = 1131
        fun build(context: Context): Intent = Intent(context, NewDiscountActivity::class.java)
    }

}