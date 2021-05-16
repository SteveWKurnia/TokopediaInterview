package com.tokopedia.maps.data

import com.google.gson.annotations.SerializedName

data class CountryDataModel(
        @SerializedName("name")
        var name: String,
        @SerializedName("capital")
        var capital: String,
        @SerializedName("population")
        var population: Long,
        @SerializedName("callingCodes")
        var callingCodes: List<String>,
        @SerializedName("latlng")
        var latlng: List<Double>
)
