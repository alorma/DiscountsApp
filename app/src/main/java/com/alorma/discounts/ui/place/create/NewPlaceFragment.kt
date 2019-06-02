package com.alorma.discounts.ui.place.create

import android.os.Bundle
import android.widget.Toast
import com.alorma.discounts.ui.base.BaseFragment

class NewPlaceFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(requireContext(), "Create", Toast.LENGTH_SHORT).show()
    }
}