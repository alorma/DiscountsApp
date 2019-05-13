package com.alorma.discounts.ui.newdiscount

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alorma.discounts.R
import kotlinx.android.synthetic.main.new_discount_activity.*

class NewDiscountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_discount_activity)

        saveButton.setOnClickListener {
            finish()
        }
    }

    companion object {
        fun build(context: Context): Intent = Intent(context, NewDiscountActivity::class.java)
    }
}