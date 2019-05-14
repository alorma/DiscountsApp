package com.alorma.discounts.ui.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.alorma.discounts.ui.widget.R

open class SelectorFieldView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    TextInputLayout(context, attributeSet, defStyleAttr) {

    var actionListener: (() -> Unit)? = null

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
        updateBoxBackgroundColor()
    }

    private fun callActionListener() {
        error = null
        isActivated = true
        isHovered = false
        actionListener?.invoke()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        updateBoxBackgroundColor()
    }

    private fun updateBoxBackgroundColor() {
        if (isEnabled) {
            setBoxBackgroundColorResource(R.color.surface)
        } else {
            setBoxBackgroundColorResource(R.color.surface_light)
        }
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            editText?.compoundDrawableTintList =
                ContextCompat.getColorStateList(context, R.color.selector_field_arrow_color)
        }
    }
}