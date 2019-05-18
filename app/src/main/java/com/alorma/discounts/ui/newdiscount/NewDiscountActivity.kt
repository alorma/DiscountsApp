package com.alorma.discounts.ui.newdiscount

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.alorma.discounts.R
import com.alorma.discounts.ui.barcode.BarcodeCaptureActivity
import com.alorma.discounts.ui.barcode.BarcodeCaptureResultData
import com.alorma.discounts.ui.base.BaseActivity
import kotlinx.android.synthetic.main.new_discount_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class NewDiscountActivity : BaseActivity(), NewDiscountViewModel.View {

    private val newDiscountViewModel: NewDiscountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_discount_activity)

        newDiscountViewModel.view = this

        configCode()
        configPlace()
        configDiscountType()
        configExpiration()
        saveButton.setOnClickListener {
            val title = titleField.editText?.text?.toString().orEmpty()
            val quantity = quantityField.editText?.text?.toString().orEmpty()

            newDiscountViewModel.onSave(title, quantity)
        }
    }

    private fun configPlace() {
        placeField.actionListener = {
            // Listener to select place
        }
    }

    private fun configCode() {
        codeField.actionListener = {
            runWithPermissions(Permission.CAMERA) {
                val intent = BarcodeCaptureActivity.buildIntent(this)
                startActivityForResult(intent, RC_CAPTURE_BARCODE)
            }
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

    companion object {
        private const val RC_CAPTURE_BARCODE = 1131
        fun build(context: Context): Intent = Intent(context, NewDiscountActivity::class.java)
    }
}