package com.alorma.discounts.ui.place.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseFragment
import com.alorma.discounts.ui.newdiscount.form.NewDiscountViewModel
import com.alorma.discounts.ui.newdiscount.form.SavePlace
import kotlinx.android.synthetic.main.select_place_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectPlaceFragment : BaseFragment(), SelectPlaceViewModel.View {

    private val newDiscountViewModel by sharedViewModel<NewDiscountViewModel>()
    private val selectPlaceViewModel by viewModel<SelectPlaceViewModel>()

    private val adapter: SelectPlaceAdapter by lazy { SelectPlaceAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectPlaceViewModel.view = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.select_place_fragment, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.createPlaceFragment -> {
                    val action = SelectPlaceFragmentDirections.actionSelectPlaceFragmentToNewPlaceFragment()
                    Navigation.findNavController(view).navigate(action)
                    true
                }
                else -> false
            }
        }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        adapter.callback = {
            newDiscountViewModel.onPlaceSelected(SavePlace(it.id, it.title))
            activity?.onBackPressed()
        }

        placeList.adapter = adapter
        placeList.layoutManager = LinearLayoutManager(requireContext())

        selectPlaceViewModel.places.observe(this) {
            adapter.submitList(it)
        }
    }
}