package com.tokopedia.maps.domain

import com.google.android.gms.maps.model.LatLng

data class Country(
        var name: String,
        var capital: String,
        var population: Long,
        var callingCodes: String,
        var latlng: LatLng)
