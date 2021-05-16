package com.tokopedia.maps.domain

class GetCountryData constructor(
        private val countryRepository: CountryRepository
) {
    fun execute(countryName: String) = countryRepository.getCountryData(countryName)
}