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
import kotlinx.android.synthetic.main.create_place_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewPlaceFragment : BaseFragment() {

    private val createPlaceViewModel: CreatePlaceViewModel by viewModel()

    private lateinit var binding: CreatePlaceFragmentBinding

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
    }
}