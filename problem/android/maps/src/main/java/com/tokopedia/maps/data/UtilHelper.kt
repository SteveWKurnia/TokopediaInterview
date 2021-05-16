package com.tokopedia.maps.data

import java.text.NumberFormat
import java.util.*

object UtilHelper {

    const val BASE_URL = "https://restcountries-v1.p.rapidapi.com/"

    fun Long.formatNumber(): String {
        val nf = NumberFormat.getNumberInstance(Locale("in", "ID"))
        return nf.format(this)
    }

}