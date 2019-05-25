package com.alorma.discounts.ui.newdiscount

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.alorma.discounts.databinding.NewDiscountFragmentBinding
import com.alorma.discounts.ui.barcode.BarcodeCaptureFragment
import com.alorma.discounts.ui.barcode.BarcodeCaptureResultData
import com.alorma.discounts.ui.base.BaseFragment
import kotlinx.android.synthetic.main.new_discount_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewDiscountFragment : BaseFragment(), NewDiscountViewModel.View {

    private lateinit var binding: NewDiscountFragmentBinding
    private val newDiscountViewModel by viewModel<NewDiscountViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NewDiscountFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newDiscountViewModel.view = this
        binding.viewModel = newDiscountViewModel
        binding.lifecycleOwner = this

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

    override fun onStart() {
        super.onStart()
        arguments?.getParcelable<BarcodeCaptureResultData>(BarcodeCaptureFragment.EXTRA_RETURN)?.let {
            newDiscountViewModel.onBarcodeCaptured(it)
        }
    }

    private fun configCode() {
        codeFieldImage.setOnClickListener {
            runWithPermissions(Permission.CAMERA) {
                if (it.isAllGranted()) {
                    // DEBUG
                    /*
                    if (BuildConfig.DEBUG) {
                        val result = BarcodeCaptureResultData("99601278037322003004", BarcodeFormat.FORMAT_CODE_128)
                        newDiscountViewModel.onBarcodeCaptured(result)
                    }
                     */

                    val destination = NewDiscountFragmentDirections.actionNewDiscountFragmentToBarcodeCaptureFragment()
                    findNavController().navigate(destination)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_CAPTURE_BARCODE && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<BarcodeCaptureResultData>(BarcodeCaptureFragment.EXTRA_RETURN)?.let {
                newDiscountViewModel.onBarcodeCaptured(it)
            }
        }
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
                        this@NewDiscountFragment.expirationField.setText("$day/$month/$year")

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
                data?.getParcelableExtra<BarcodeCaptureResultData>(BarcodeCaptureFragment.EXTRA_RETURN)?.let {
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
    }

}