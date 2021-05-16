package com.tokopedia.maps.app

import com.tokopedia.maps.domain.Country

interface MapsContract {

    interface View {
        fun setData(country: Country)

        fun onError()
    }

    interface Presenter {
        fun getCountryData(countryName: String)
    }

}