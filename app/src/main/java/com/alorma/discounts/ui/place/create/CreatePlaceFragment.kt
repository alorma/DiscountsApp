package com.alorma.discounts.ui.place.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.datetime.timePicker
import com.alorma.discounts.R
import com.alorma.discounts.databinding.CreatePlaceFragmentBinding
import com.alorma.discounts.extensions.setupBack
import com.alorma.discounts.ui.base.BaseFragment
import com.alorma.discounts.ui.formatter.TimeFormat
import com.alorma.discounts.ui.newdiscount.form.NewDiscountViewModel
import com.alorma.discounts.ui.newdiscount.form.SavePlace
import kotlinx.android.synthetic.main.create_place_fragment.*
import org.joda.time.LocalTime
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CreatePlaceFragment : BaseFragment(), CreatePlaceViewModel.View {

    private val timeFormat by inject<TimeFormat>()
    private val createPlaceViewModel by viewModel<CreatePlaceViewModel>()
    private val newPlaceViewModel by sharedViewModel<NewDiscountViewModel>()

    private lateinit var binding: CreatePlaceFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPlaceViewModel.view = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreatePlaceFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBack(toolbar)

        saveButton.setOnClickListener {
            val name = nameField.editText?.text?.toString().orEmpty()
            val address = addressField.editText?.text?.toString()
            createPlaceViewModel.save(name, address)
        }

        openHourField.actionListener = { field ->
            showTimeSelect { time ->
                field.setText(timeFormat.format(time))
                createPlaceViewModel.onOpenHourSelected(time)
            }
        }
        closeHourField.actionListener = { field ->
            showTimeSelect { time ->
                field.setText(timeFormat.format(time))
                createPlaceViewModel.onCloseHourSelected(time)
            }
        }
    }

    private fun showTimeSelect(timeSelected: (LocalTime) -> Unit) {
        MaterialDialog(requireContext(), BottomSheet()).show {
            timePicker { _, datetime ->
                val time = LocalTime(
                    datetime.get(Calendar.HOUR_OF_DAY),
                    datetime.get(Calendar.MINUTE)
                )
                timeSelected(time)
            }
        }
    }

    override fun close(savePlace: SavePlace) {
        newPlaceViewModel.onPlaceSelected(savePlace)
        findNavController().popBackStack(R.id.newDiscountFragment, false)
    }
}
