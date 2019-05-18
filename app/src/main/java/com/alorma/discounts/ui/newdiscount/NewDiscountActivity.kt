package com.alorma.discounts.ui.newdiscount

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.alorma.discounts.R
import com.alorma.discounts.ui.barcode.BarcodeCaptureActivity
import com.alorma.discounts.ui.base.BaseActivity
import kotlinx.android.synthetic.main.new_discount_activity.*

class NewDiscountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_discount_activity)

        saveButton.setOnClickListener {
            finish()
        }

        codeField.actionListener = {
            runWithPermissions(Permission.CAMERA) {
                val intent = BarcodeCaptureActivity.buildIntent(this)
                startActivity(intent)
            }
        }

        placeField.actionListener = {
            // Listener to select place
        }
    }

    companion object {
        fun build(context: Context): Intent = Intent(context, NewDiscountActivity::class.java)
    }
}