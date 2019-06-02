package com.alorma.discounts.ui.place.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.alorma.discounts.databinding.CreatePlaceFragmentBinding
import com.alorma.discounts.ui.base.BaseFragment
import com.alorma.discounts.ui.newdiscount.form.NewDiscountViewModel
import com.alorma.discounts.ui.newdiscount.form.SavePlace
import kotlinx.android.synthetic.main.create_place_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatePlaceFragment : BaseFragment(), CreatePlaceViewModel.View {

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

        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appbarConfig)
        toolbar.setOnMenuItemClickListener { it.onNavDestinationSelected(navController) }

        saveButton.setOnClickListener {
            val name = nameField.editText?.text?.toString().orEmpty()
            createPlaceViewModel.save(name)
        }
    }

    override fun close(savePlace: SavePlace) {
        newPlaceViewModel.onPlaceSelected(savePlace)
        findNavController().navigateUp()
    }
}