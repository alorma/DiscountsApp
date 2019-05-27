package com.alorma.discounts.ui.newdiscount.form

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NavUtils
import androidx.navigation.fragment.findNavController
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.alorma.discounts.databinding.NewDiscountFragmentBinding
import com.alorma.discounts.ui.base.BaseFragment
import com.alorma.discounts.ui.newdiscount.barcode.BarcodeCaptureFragment
import com.alorma.discounts.ui.newdiscount.barcode.BarcodeCaptureResultData
import kotlinx.android.synthetic.main.new_discount_fragment.codeFieldImage
import kotlinx.android.synthetic.main.new_discount_fragment.descriptionField
import kotlinx.android.synthetic.main.new_discount_fragment.expirationField
import kotlinx.android.synthetic.main.new_discount_fragment.placeField
import kotlinx.android.synthetic.main.new_discount_fragment.saveButton
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.Calendar

class NewDiscountFragment : BaseFragment(), NewDiscountViewModel.View {

    private lateinit var binding: NewDiscountFragmentBinding
    private val newDiscountViewModel by sharedViewModel<NewDiscountViewModel>()

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
        configExpiration()
        configPlace()
        configSave()
    }

    private fun configCode() {
        codeFieldImage.setOnClickListener {
            runWithPermissions(Permission.CAMERA) {
                if (it.isAllGranted()) {
                    val destination = NewDiscountFragmentDirections
                            .actionNewDiscountFragmentToBarcodeCaptureFragment()
                    findNavController().navigate(destination)
                }
            }
        }
    }

    private fun configExpiration() {
        expirationField.actionListener = {
            MaterialDialog(requireContext()).show {
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

    private fun configPlace() {
        placeField.actionListener = {
            newDiscountViewModel.onPlaceSelected(SavePlace("la-sirena", "La Sirena"))
        }
    }

    private fun configSave() {
        saveButton.setOnClickListener {
            val text = descriptionField.editText?.text?.toString().orEmpty()
            newDiscountViewModel.onSave(text)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_CAPTURE_BARCODE && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<BarcodeCaptureResultData>(
                    BarcodeCaptureFragment.EXTRA_RETURN)?.let {
                newDiscountViewModel.onBarcodeCaptured(it)
            }
        }
    }

    override fun onError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun close() {
        NavUtils.navigateUpFromSameTask(requireActivity())
    }

    companion object {
        private const val RC_CAPTURE_BARCODE = 1131
    }
}