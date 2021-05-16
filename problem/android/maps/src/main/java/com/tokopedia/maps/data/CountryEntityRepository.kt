package com.tokopedia.maps.data

import com.google.android.gms.maps.model.LatLng
import com.tokopedia.maps.domain.Country
import com.tokopedia.maps.domain.CountryRepository
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryEntityRepository: CountryRepository {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(UtilHelper.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    override fun getCountryData(countryName: String): Observable<Country> {
        return retrofit.create(DataRetrofitInterface::class.java).getCountryData(
                countryName
        ).map {
            Country(
                    name = it[0].name,
                    capital = it[0].capital,
                    population = it[0].population,
                    latlng = LatLng(it[0].latlng.getOrElse(0) { 0.0 }, it[0].latlng.getOrElse(1) { 0.0 }),
                    callingCodes = it[0].callingCodes[0])
        }
    }
}