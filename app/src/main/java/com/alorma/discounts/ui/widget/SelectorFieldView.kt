package com.alorma.discounts.ui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.alorma.discounts.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

open class SelectorFieldView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attributeSet, defStyleAttr) {

    var actionListener: ((SelectorFieldView) -> Unit)? = null

    init {
        val editText = object : TextInputEditText(this.context) {
            override fun getDefaultEditable(): Boolean = false
        }.apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }

        editText.setOnClickListener {
            if (isEnabled) {
                if (it.isFocused) {
                    callActionListener()
                } else {
                    isActivated = false
                }
            }
        }

        editText.setOnFocusChangeListener { _, hasFocus ->
            if (isEnabled) {
                if (hasFocus) {
                    callActionListener()
                } else {
                    isActivated = false
                }
            }
        }

        addView(editText)

        configureArrow()
    }

    private fun callActionListener() {
        error = null
        isActivated = true
        isHovered = false
        actionListener?.invoke(this)
    }

    override fun setError(errorText: CharSequence?) {
        if (isEnabled) {
            super.setError(errorText)
            isActivated = false
        }
    }

    fun setText(text: CharSequence?) {
        isActivated = false
        editText?.setText(text)
    }

    private fun configureArrow() {
        val arrowDrawable = ContextCompat.getDrawable(context, R.drawable.ic_field_selector_arrow)
        editText?.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowDrawable, null)
    }
}