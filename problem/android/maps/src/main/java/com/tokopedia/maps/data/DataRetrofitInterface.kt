package com.tokopedia.maps.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DataRetrofitInterface {

    @Headers(
            "x-rapidapi-key: 1126b7efb4msh8f55e7d4e4bac01p1ce42ajsn4d85dd53e1b2",
            "x-rapidapi-host: restcountries-v1.p.rapidapi.com"
    )
    @GET("name/{searched_country}")
    fun getCountryData(@Path("searched_country") name: String): Observable<List<CountryDataModel>>

}