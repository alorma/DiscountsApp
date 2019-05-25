package com.alorma.discounts.ui.discountdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.alorma.discounts.databinding.DiscountDetailFragmentBinding
import com.alorma.discounts.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DiscountDetailFragment : BaseFragment(), DiscountDetailViewModel.View {

    private lateinit var binding: DiscountDetailFragmentBinding
    private val args: DiscountDetailFragmentArgs by navArgs()
    private val detailViewModel by viewModel<DiscountDetailViewModel> {
        parametersOf(args)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DiscountDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel
    }
}