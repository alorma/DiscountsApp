package com.alorma.discounts.ui.discountslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseFragment
import kotlinx.android.synthetic.main.discounts_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscountsListFragment : BaseFragment(), DiscountsViewModel.View {

    private val discountsViewModel by viewModel<DiscountsViewModel>()
    private val adapter: DiscountsAdapter = DiscountsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.discounts_list_fragment, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.callback = { discountsViewModel.onDiscountClick(it) }
        discountsViewModel.view = this

        addDiscount.setOnClickListener {
            discountsViewModel.onCreateNew()
        }

        discountsList.adapter = adapter
        discountsList.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun showDiscounts(discounts: List<DiscountVM>) {
        adapter.items = discounts
    }

    override fun openDetail(discountId: String) {
        val directions = DiscountsListFragmentDirections.actionDiscountsListFragmentToDiscountDetailFragment(discountId)
        findNavController().navigate(directions)
    }

    override fun openNewDiscount() {
        val directions = DiscountsListFragmentDirections.actionDiscountsListFragmentToNewDiscountFragment()
        findNavController().navigate(directions)
    }
}
