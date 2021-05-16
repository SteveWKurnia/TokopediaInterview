package com.tokopedia.maps.app

import com.tokopedia.maps.data.CountryEntityRepository
import com.tokopedia.maps.domain.Country
import com.tokopedia.maps.domain.GetCountryData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MapsPresenter constructor(private val view: MapsContract.View): MapsContract.Presenter {

    private val getCountryData = GetCountryData(CountryEntityRepository())

    override fun getCountryData(countryName: String) {
        getCountryData.execute(countryName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableObserver<Country>() {
                    override fun onNext(t: Country) {
                        view.setData(t)
                    }

                    override fun onError(e: Throwable) {
                        view.onError()
                    }

                    override fun onComplete() {
                        //no-implementation
                    }

                })
    }

}