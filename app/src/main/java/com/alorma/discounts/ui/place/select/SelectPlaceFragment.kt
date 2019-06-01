package com.alorma.discounts.ui.place.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.discounts.R
import com.alorma.discounts.ui.base.BaseFragment
import com.alorma.discounts.ui.newdiscount.form.NewDiscountViewModel
import kotlinx.android.synthetic.main.select_place_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SelectPlaceFragment : BaseFragment(), SelectPlaceViewModel.View {

    private val newDiscountViewModel by sharedViewModel<NewDiscountViewModel>()
    private val selectPlaceViewModel by sharedViewModel<SelectPlaceViewModel>()

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

        placeList.adapter = adapter
        placeList.layoutManager = LinearLayoutManager(requireContext())

        selectPlaceViewModel.places.observe(this) {
            adapter.submitList(it)
        }
    }
}