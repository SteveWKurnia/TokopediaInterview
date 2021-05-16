package com.tokopedia.maps.domain

import com.tokopedia.maps.data.CountryDataModel
import io.reactivex.Observable

interface CountryRepository {

    fun getCountryData(countryName: String): Observable<Country>


}