package com.alorma.discounts.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow

class ListSelectorFieldView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    SelectorFieldView(context, attributeSet, defStyleAttr) {

    private lateinit var listPopupWindow: ListPopupWindow

    fun <T> show(items: List<T>, itemMap: (T) -> String, onItemSelected: (T) -> Unit) {
        if (isEnabled) {
            preparePopupWindow(items, itemMap, onItemSelected)
            listPopupWindow.show()
        }
    }

    private fun <T> preparePopupWindow(
        items: List<T>,
        itemMap: (T) -> String,
        onItemSelected: (T) -> Unit
    ) {
        listPopupWindow = ListPopupWindow(context).apply {
            anchorView = editText
            val adapter = createAdapter(items, itemMap)
            setAdapter(adapter)
            setOnItemClickListener { _, _, position, _ ->
                items.getOrNull(position)?.let { item ->
                    onItemSelected(item)
                    editText?.setText(itemMap(item))
                    dismiss()
                    isActivated = false
                }
            }
            setOnDismissListener {
                isActivated = false
            }
        }
        val touchListener = listPopupWindow.createDragToOpenListener(this)
        editText?.setOnTouchListener(touchListener)
    }

    private fun <T> createAdapter(
        items: List<T>,
        itemMap: (T) -> String
    ): ArrayAdapter<String> {
        return object : ArrayAdapter<String>(context, android.R.layout.simple_list_item_1) {
            override fun getCount(): Int = items.size
            override fun getItem(position: Int): String = itemMap(items[position])
        }
    }
}