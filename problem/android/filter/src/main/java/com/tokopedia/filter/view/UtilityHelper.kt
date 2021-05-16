package com.tokopedia.filter.view

import java.text.NumberFormat
import java.util.*

object UtilityHelper {

    fun Int.formatPrice(): String {
        val locale = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(locale)
        return formatter.format(this)
    }

}